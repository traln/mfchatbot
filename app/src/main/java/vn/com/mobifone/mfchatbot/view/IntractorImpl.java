package vn.com.mobifone.mfchatbot.view;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.com.mobifone.mfchatbot.model.MarsPhotosList;
import vn.com.mobifone.mfchatbot.common.ApiClient;
import vn.com.mobifone.mfchatbot.service.ChatbotService;

/**
 * Created by Lê Nguyên Trà on 18/11/2021.
 * Copyright © 2021 VNPT IT 3. All rights reserved.
 */
public class IntractorImpl implements MainContract.Intractor {
    @Override
    public void getMarsPhotosArrayList(final OnFinishedListener onFinishedListener) {

        ChatbotService dataService = ApiClient.getRetrofitInstance().create(ChatbotService.class);

        Call<MarsPhotosList> marsPhotosListCall = dataService.getMarsPhotoListCall();
        String msg = marsPhotosListCall.request().url().toString();
        Log.d("Retrofit: ", msg);

        marsPhotosListCall.enqueue(new Callback<MarsPhotosList>() {
            @Override
            public void onResponse(Call<MarsPhotosList> call, Response<MarsPhotosList> response) {
                String msg = response.body().getMarsPhotosList().toString();
                Log.d("Retrofit body: ", msg);
                onFinishedListener.onFinished(response.body().getMarsPhotosList());

            }

            @Override
            public void onFailure(Call<MarsPhotosList> call, Throwable error) {
                Log.d("Retrofit: ", "Something went wrong");
                onFinishedListener.onFailure(error);

            }
        });
    }

}
