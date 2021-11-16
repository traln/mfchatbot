package vn.com.mobifone.mfchatbot.database.service;

import java.util.List;
import io.reactivex.Flowable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import vn.com.mobifone.mfchatbot.database.model.ChatBotModel;

public interface ChatBotService {
    @GET("api/Chatbot")
    Flowable<ChatBotModel> addChatBot(@Query("username") String p_username,@Query("content") String p_content,@Query("state") Integer p_state);
}