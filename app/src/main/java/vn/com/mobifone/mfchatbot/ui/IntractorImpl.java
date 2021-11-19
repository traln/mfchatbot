package vn.com.mobifone.mfchatbot.ui;

import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.com.mobifone.mfchatbot.model.ChatBotModel;
import vn.com.mobifone.mfchatbot.model.MarsPhotosList;
import vn.com.mobifone.mfchatbot.common.ApiClient;
import vn.com.mobifone.mfchatbot.param.ChatBotParam;
import vn.com.mobifone.mfchatbot.service.ChatbotService;

/**
 * Created by Lê Nguyên Trà on 18/11/2021.
 * Copyright © 2021 VNPT IT 3. All rights reserved.
 */
public class IntractorImpl implements MainContract.Intractor {


    @Override
    public void addChatBot(OnFinishedListener onFinishedListener, ChatBotParam param) {
        ChatbotService dataService = ApiClient.getRetrofitInstance().create(ChatbotService.class);

        Call<List<ChatBotModel>> marsPhotosListCall = dataService.addChatBot(param);
        String msg = marsPhotosListCall.request().url().toString();
        Log.d("Retrofit: ", msg);

        marsPhotosListCall.enqueue(new Callback<List<ChatBotModel>>() {
            @Override
            public void onResponse(Call<List<ChatBotModel>> call, Response<List<ChatBotModel>> response) {

                onFinishedListener.onFinished(response.body().get(0));

            }

            @Override
            public void onFailure(Call<List<ChatBotModel>> call, Throwable error) {
                Log.d("Retrofit: ", "Something went wrong");
                onFinishedListener.onFailure(error);

            }
        });
    }

}
