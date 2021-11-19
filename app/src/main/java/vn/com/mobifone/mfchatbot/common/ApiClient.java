package vn.com.mobifone.mfchatbot.common;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Lê Nguyên Trà on 18/11/2021.
 * Copyright © 2021 VNPT IT 3. All rights reserved.
 */
public class ApiClient {
    private static Retrofit retrofit;
    private static final String BASE_URL = "http://101.99.51.36:9080/";

    //If null creates an instance else returns the existing instance
    //This prevents redundant instances
    public static Retrofit getRetrofitInstance(){

        if(retrofit == null){
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
