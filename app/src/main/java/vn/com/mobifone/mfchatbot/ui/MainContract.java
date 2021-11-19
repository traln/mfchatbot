package vn.com.mobifone.mfchatbot.ui;

import java.util.ArrayList;
import java.util.List;

import vn.com.mobifone.mfchatbot.model.ChatBotModel;
import vn.com.mobifone.mfchatbot.model.MarsPhotos;
import vn.com.mobifone.mfchatbot.param.ChatBotParam;

/**
 * Created by Lê Nguyên Trà on 18/11/2021.
 * Copyright © 2021 VNPT IT 3. All rights reserved.
 */
public interface MainContract {

    interface Presenter{

        void onDestroy();


        void addChatBot(ChatBotParam param);

    }

    interface MainView {

        void showProgress();

        void hideProgress();
        void setLoadingProgressBar(String message, boolean active);

        void setDataToRecyclerView(ArrayList<MarsPhotos> noticeArrayList);
        void setDataChatBot(ChatBotModel rs);

        void onResponseFailure(Throwable throwable);

    }

    /**
     * This is for fetching data from data source.
     **/
    interface Intractor {

        interface OnFinishedListener {

            void onFinished(ChatBotModel list);
            void onFailure(Throwable t);

            void onAddChatBotFinished(ChatBotModel list);
            void onAddChatBotFailure(Throwable t);
        }

        void addChatBot(OnFinishedListener onFinishedListener, ChatBotParam param);

    }
}
