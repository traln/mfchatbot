package vn.com.mobifone.mfchatbot.service;

import retrofit2.Call;
import retrofit2.http.GET;
import vn.com.mobifone.mfchatbot.model.MarsPhotosList;

/**
 * Created by Lê Nguyên Trà on 18/11/2021.
 * Copyright © 2021 VNPT IT 3. All rights reserved.
 */
public interface ChatbotService {
    @GET("mars-photos/api/v1/rovers/curiosity/photos?sol=1000&api_key=DEMO_KEY")
    Call<MarsPhotosList> getMarsPhotoListCall();
}
