package vn.com.mobifone.mfchatbot.database.service.core;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.HttpException;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String BASE_AUTH_URL = "http://10.59.90.51:999/";
    private static ApiClient sInstance;
    private HashMap<ModuleServiceType, Object> services = new HashMap<>();

    private ApiClient() {
    }
    public static ApiClient getInstance() {
        if (sInstance == null) {
            sInstance = new ApiClient();
        }
        return sInstance;
    }

    public static void clearAllServiceExceptAuthSystem() {
        List<ModuleServiceType> list = new ArrayList<>();
        for (ModuleServiceType moduleServiceType : getInstance().services.keySet()) {
            if (moduleServiceType != ModuleServiceType.SYSTEM_SERVICE)
                list.add(moduleServiceType);
        }

        for (ModuleServiceType moduleServiceType : list) {
            getInstance().services.remove(moduleServiceType);
        }
    }

    public static Flowable<Object> getService(final ModuleServiceType moduleServiceType) {
        if (!getInstance().services.containsKey(moduleServiceType)) {
            if (!getInstance().services.containsKey(ModuleServiceType.SYSTEM_SERVICE)) {
                return Flowable.error(new Exception("Khởi tạo Module Service không thành công"));
            }

            //Check
//            String accessToken = UserLoginRepository.getInstance().getAccessTokenOfModule(moduleServiceType.getModuleType().getValue());
//            if (accessToken == null || accessToken.isEmpty()) {
//                return getInstance().loginModule(moduleServiceType.getModuleType())
//                        .toFlowable()
//                        .flatMap((Function<UserLogin, Publisher<Object>>) userLogin -> {
//                            if (userLogin != null /*&& userLogin.isLoginOk()*/) {
//                                UserLoginRepository.getInstance().saveAccessTokenOfModule(moduleServiceType.getModuleType().getValue(), userLogin.getAuthToken());
//
//                                if (getInstance().initRetrofitModuleService(moduleServiceType))
//                                    return Flowable.just(Objects.requireNonNull(getInstance().services.get(moduleServiceType)));
//                                else
//                                    return Flowable.error(new Exception("Khởi tạo Module Service không thành công"));
//                            } else {
//                                return Flowable.error(new Exception("Khởi tạo Module Service không thành công"));
//                            }
//                        });
//            } else {
            if (getInstance().initRetrofitModuleService(moduleServiceType))
                return Flowable.just(Objects.requireNonNull(getInstance().services.get(moduleServiceType)));
            else
                return Flowable.error(new Exception("Khởi tạo Module Service không thành công"));
//            }
        } else
            return Flowable.just(Objects.requireNonNull(getInstance().services.get(moduleServiceType)));
    }

    public boolean initRetrofitModuleService(final ModuleServiceType moduleServiceType) {
        if (services.containsKey(moduleServiceType)) {
            return true;
        }

        //ModuleInfo
        final String serviceUri = moduleServiceType == ModuleServiceType.SYSTEM_SERVICE ? BASE_AUTH_URL : UserLoginRepository.getInstance().getServiceUriOfMudule(moduleServiceType.getModuleType().getValue());
        if (serviceUri == null) {
            return false;
        }

        Class serviceClass;
        try {
            serviceClass = Class.forName(moduleServiceType.getServiceName());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        // init Gson
        BooleanAdapter booleanAdapter = new BooleanAdapter();
        IntegerAdapter integerAdapter = new IntegerAdapter();
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Boolean.class, booleanAdapter)
                .registerTypeAdapter(boolean.class, booleanAdapter)
                .registerTypeAdapter(Integer.class, integerAdapter)
                .registerTypeAdapter(int.class, integerAdapter)
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        //Interceptor
        final Interceptor interceptor = chain -> {
//            String accessToken = moduleServiceType == ModuleServiceType.SYSTEM_SERVICE ? UserLoginRepository.getInstance().getCurrentAuthToken() : UserLoginRepository.getInstance().getAccessTokenOfModule(moduleServiceType.getModuleType().getValue());
            String accessToken = UserLoginRepository.getInstance().getCurrentAuthToken();
            Request request = chain.request().newBuilder().addHeader(HEADER_AUTH, AUTH_PREFIX + accessToken).build();
            return chain.proceed(request);
        };

        // init OkHttpClient
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.addInterceptor(interceptor);
        okHttpClient.connectTimeout(TIMEOUT_CONNECTION, TimeUnit.SECONDS);
        okHttpClient.readTimeout(TIMEOUT_CONNECTION, TimeUnit.SECONDS);
        okHttpClient.writeTimeout(TIMEOUT_CONNECTION, TimeUnit.SECONDS);

        // RestAdapter
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(serviceUri)
                .client(okHttpClient.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        // init Service
        @SuppressWarnings("unchecked")
        Object service = retrofit.create(serviceClass);

        services.put(moduleServiceType, service);
        return true;
    }

    public static String getError(final Throwable throwable) {
        String err = null;
        if (throwable instanceof HttpException) {
            ResponseBody body = ((HttpException) throwable).response().errorBody();
            if (body != null) {
                try {
                    err = body.string();
                    if (err.contains("An error has occurred.")) {
                        SessionManager.getInstance().endSessionReLogin();
                    } else {
                        if (!BuildConfig.IS_DEBUG) {
                            String[] arrMessage = err.split("ORA-");
                            if (arrMessage.length > 1)//Phát sinh ORA- thì size luôn > 1
                                err = arrMessage[1].split(":")[1];
                            else
                                err = arrMessage[0];
                            err = err.replace("\\n", "").replace("\\r", "");
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    // unknown error
                    err = "Lỗi không xác định: " + throwable.getMessage() + " / " + throwable.getClass();
                }
            }
        } else if (throwable instanceof SocketTimeoutException) {
            // handle timeout from Retrofit
            err = "Timeout: " + throwable.getMessage() + " / " + throwable.getClass();
        } else if (throwable instanceof ConnectException) {
            // A network error happened
            err = "Vui lòng kiểm tra kết nối mạng";
        } else if (throwable instanceof IOException) {
            // A network error happened
            if (throwable instanceof SocketException) {
                err = "Lỗi kết nối: quá thời gian hồi đáp!";
            } else {
                err = "Lỗi kết nối: " + throwable.getMessage() + " / " + throwable.getClass();
            }
        } else {
            // unknown error
            err = "Lỗi không xác định: " + throwable.getMessage() + " / " + throwable.getClass();
        }

        return err;
    }

    /*private SystemService getLoginServiceModule(final ModuleType moduleType) {
        //ModuleInfo
        final String serviceUri = UserLoginRepository.getInstance().getServiceUriOfMudule(moduleType.getValue());

        if (serviceUri == null)
            return null;

        // init OkHttpClient
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.connectTimeout(TIMEOUT_CONNECTION, TimeUnit.SECONDS);
        okHttpClient.readTimeout(TIMEOUT_CONNECTION, TimeUnit.SECONDS);
        okHttpClient.writeTimeout(TIMEOUT_CONNECTION, TimeUnit.SECONDS);

        // RestAdapter
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(serviceUri)
                .client(okHttpClient.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // init Service
        return retrofit.create(SystemService.class);
    }

    private Single<UserLogin> loginModule(final ModuleType moduleType) {

        SystemService loginService = getLoginServiceModule(moduleType);
        if (loginService == null)
            return Single.error(new Exception("Bạn chưa được gán quyền sử dụng chức năng này"));

        UserLogin userLogin = UserLoginRepository.getInstance().getUserLogin();

        return loginService.loginUser("password",
                userLogin.getMaDangNhap(),
                userLogin.getMatKhau(),
                MyMobile.getSimNumber(),
                MyMobile.getPhoneNumber(),
                MyMobile.getDeviceId(),
                MyMobile.getCurrentClientVersion(),
                MyMobile.getDeviceName() + " - " + MyMobile.getOSVersion(),
                Constant.OsType.ANDROID,
                "2");
    }*/

    public static BaseResponse<String> getErrorResponse(final Throwable throwable) {
        String err = "";
        if (throwable instanceof HttpException) {
            ResponseBody body = ((HttpException) throwable).response().errorBody();
            if (body != null) {
                try {
                    if (BuildConfig.IS_DEBUG) {
                        err = body.string();
                    } else {
                        String[] arrMessage = body.string().split("ORA-");
                        if (arrMessage.length > 1)//Phát sinh ORA- thì size luôn > 1
                            err = arrMessage[1].split(":")[1];
                        else
                            err = arrMessage[0];
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    // unknown error
                    err = "Lỗi không xác định: " + throwable.getMessage() + " / " + throwable.getClass();
                }
            }
        } else if (throwable instanceof SocketTimeoutException) {
            // handle timeout from Retrofit
            err = "Timeout: " + throwable.getMessage() + " / " + throwable.getClass();
        } else if (throwable instanceof ConnectException) {
            // A network error happened
            err = "Vui lòng kiểm tra kết nối mạng";
        } else if (throwable instanceof IOException) {
            // A network error happened
            err = "Lỗi kết nối: " + throwable.getMessage() + " / " + throwable.getClass();
        } else {
            // unknown error
            err = "Lỗi không xác định: " + throwable.getMessage() + " / " + throwable.getClass();
        }

        BaseResponse<String> response = new BaseResponse<>();
        response.setData(err);
        response.setMessage(StringUtils.getMsgFromOraMsg(err));
        response.setStatus(StringUtils.getCodeFromOraMsg(err));

        return response;
    }
}