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
public class MainPresenterImpl implements MainContract.Presenter, MainContract.Intractor.OnFinishedListener {

    private MainContract.MainView mainView;
    private MainContract.Intractor intractor;

    public MainPresenterImpl(MainContract.MainView mainView, MainContract.Intractor intractor){
        this.mainView = mainView;
        this.intractor = intractor;
    }


    @Override
    public void onDestroy() {
        this.mainView = null;
    }


    @Override
    public void addChatBot(ChatBotParam param) {
        intractor.addChatBot(this,param);
    }


    @Override
    public void onFinished(ChatBotModel list) {
        if(this.mainView != null){
            mainView.setDataChatBot(list);
            mainView.hideProgress();
        }
    }

    @Override
    public void onFailure(Throwable t) {
        if(this.mainView != null){
            mainView.onResponseFailure(t);
            mainView.hideProgress();
        }
    }

    @Override
    public void onAddChatBotFinished(ChatBotModel id) {
        if(this.mainView != null){
            mainView.setDataChatBot(id);
            mainView.hideProgress();
        }
    }

    @Override
    public void onAddChatBotFailure(Throwable t) {
        if(this.mainView != null){
            mainView.onResponseFailure(t);
            mainView.hideProgress();
        }
    }
}
