package vn.com.mobifone.mfchatbot.param;



import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.Data;

/**
 * Created by Lê Nguyên Trà on 11/19/2021.
 * Copyright © 2021 VNPT IT 3. All rights reserved.
 */
@Data
public class ChatBotParam implements Serializable {
    @SerializedName("P_USERNAME")
    private String username;
    @SerializedName("P_CONTENTS")
    private String contents;

    public ChatBotParam(String username, String contents) {
        this.username = username;
        this.contents = contents;
    }
}
