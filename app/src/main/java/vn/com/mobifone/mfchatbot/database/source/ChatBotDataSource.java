package vn.com.mobifone.mfchatbot.database.source;

import java.util.List;

import io.reactivex.Flowable;
import vn.com.mobifone.mfchatbot.database.model.ChatBotModel;

public interface ChatBotDataSource {
    Flowable<ChatBotModel> addChatBot(String p_username,
                                      String p_content,
                                      Integer p_state);
}