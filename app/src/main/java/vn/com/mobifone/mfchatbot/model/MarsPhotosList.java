package vn.com.mobifone.mfchatbot.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Lê Nguyên Trà on 18/11/2021.
 * Copyright © 2021 VNPT IT 3. All rights reserved.
 */
public class MarsPhotosList {

    @SerializedName("photos")
    private ArrayList<MarsPhotos> marsPhotosList;

    public ArrayList<MarsPhotos> getMarsPhotosList(){
        return marsPhotosList;
    }

    public void setMarsPhotosList(ArrayList<MarsPhotos> marsPhotosList){
        this.marsPhotosList = marsPhotosList;
    }
}
