package vn.com.mobifone.mfchatbot.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Lê Nguyên Trà on 18/11/2021.
 * Copyright © 2021 VNPT IT 3. All rights reserved.
 */
public class MarsPhotos {
    @SerializedName("earth_date")
    private String earthDate;

    @SerializedName("camera")
    private Camera camera;

    @SerializedName("img_src")
    private String imgSrc;

    public MarsPhotos(String earthDate, Camera camera, String imgSrc) {
        this.earthDate = earthDate;
        this.camera = camera;
        this.imgSrc = imgSrc;
    }


    public String getEarthDate() {
        return earthDate;
    }

    public String getCamera() {
        return camera.getFullName();
    }

    public String getImgSrc() {
        return imgSrc;
    }

    class Camera{
        @SerializedName("full_name")
        private String fullName;

        public Camera(String fullName) {
            super();
            this.fullName = fullName;
        }

        public String getFullName() {
            return fullName;
        }

    }
}
