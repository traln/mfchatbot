package vn.com.mobifone.mfchatbot.database.source;

import org.reactivestreams.Publisher;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import vn.com.mobifone.mfchatbot.database.model.ChatBotModel;
import vn.com.mobifone.mfchatbot.database.service.core.ApiClient;

public class ChatBotRepository implements ChatBotDataSource{
    private static ChatBotRepository INSTANCE = null;

    public static ChatBotRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ChatBotRepository();
        }
        return INSTANCE;
    }
    @Override
    public Flowable<ChatBotModel> addChatBot(String p_username, String p_content, Integer p_state) {
        return ApiClient.getService(ModuleServiceType.BANHANG_QUANLY_LAPDAT)
                .flatMap((Function<Object, Publisher<List<BanHang_GiaoPhieu>>>) o -> {
                    BH_QuanlyLapdat service = (BH_QuanlyLapdat) o;
                    return service.ad(p_id_donhang_chitiet, p_id_huonggiao,p_id_dvi_nhan);
                });
    }
}