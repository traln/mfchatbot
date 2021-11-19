package vn.com.mobifone.mfchatbot.ui;

import java.util.List;

import vn.com.mobifone.mfchatbot.model.ChatBotModel;
import vn.com.mobifone.mfchatbot.param.ChatBotParam;
import vn.com.mobifone.mfchatbot.ui.base.BasePresenter;
import vn.com.mobifone.mfchatbot.ui.base.BaseView;

/**
 * Created by Lê Nguyên Trà on 11/19/2021.
 * Copyright © 2021 VNPT IT 3. All rights reserved.
 */
public class ChatBotContract {
    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);
        void setLoadingProgressBar(String message, boolean active);
        void showResult(ChatBotModel data);

        void showNoResult(String msg);

        boolean isActive();
//        void showDanhMucSuccess(List<CategoryItem> csdh_nhanViens);
        void showError();
    }

    interface Presenter extends BasePresenter {
        void loadDanhMucNhanVien(String p_id_donvi,String p_id_diaban);
        void addChatBot(ChatBotParam param);
        void dispose();
    }
}
