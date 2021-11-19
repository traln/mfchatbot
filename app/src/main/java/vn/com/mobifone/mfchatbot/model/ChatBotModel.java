package vn.com.mobifone.mfchatbot.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.Data;

/**
 * Created by Lê Nguyên Trà on 11/19/2021.
 * Copyright © 2021 VNPT IT 3. All rights reserved.
 */
@Data
public class ChatBotModel implements Serializable {
    @SerializedName("ID")
    @Expose
    private Integer id;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("requesttime")
    @Expose
    private String requesttime;
    @SerializedName("contents")
    @Expose
    private String contents;
    @SerializedName("state")
    @Expose
    private Integer state;
    @SerializedName("imgpath01")
    @Expose
    private String imgpath01;
    @SerializedName("imgpath02")
    @Expose
    private String imgpath02;
    @SerializedName("imgpath03")
    @Expose
    private String imgpath03;
    @SerializedName("imgpath04")
    @Expose
    private String imgpath04;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRequesttime() {
        return requesttime;
    }

    public void setRequesttime(String requesttime) {
        this.requesttime = requesttime;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Object getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Object getImgpath01() {
        return imgpath01;
    }

    public void setImgpath01(String imgpath01) {
        this.imgpath01 = imgpath01;
    }

    public Object getImgpath02() {
        return imgpath02;
    }

    public void setImgpath02(String imgpath02) {
        this.imgpath02 = imgpath02;
    }

    public Object getImgpath03() {
        return imgpath03;
    }

    public void setImgpath03(String imgpath03) {
        this.imgpath03 = imgpath03;
    }

    public Object getImgpath04() {
        return imgpath04;
    }

    public void setImgpath04(String imgpath04) {
        this.imgpath04 = imgpath04;
    }
}
