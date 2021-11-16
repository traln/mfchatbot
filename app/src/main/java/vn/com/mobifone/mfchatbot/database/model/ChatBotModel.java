package vn.com.mobifone.mfchatbot.database.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
@Data
public class ChatBotModel {
    @SerializedName("ID")
    @Expose
    public Integer id;
    @SerializedName("username")
    @Expose
    public String username;
    @SerializedName("requesttime")
    @Expose
    public String requesttime;
    @SerializedName("contents")
    @Expose
    public String contents;
    @SerializedName("state")
    @Expose
    public Integer state;
    @SerializedName("imgpath01")
    @Expose
    public String imgpath01;
    @SerializedName("imgpath02")
    @Expose
    public String imgpath02;
    @SerializedName("imgpath03")
    @Expose
    public String imgpath03;
    @SerializedName("imgpath04")
    @Expose
    public String imgpath04;


}