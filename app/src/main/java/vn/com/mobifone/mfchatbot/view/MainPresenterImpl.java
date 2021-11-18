package vn.com.mobifone.mfchatbot.view;

import java.util.ArrayList;

import vn.com.mobifone.mfchatbot.model.MarsPhotos;

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
    public void requestDataFromServer() {
        intractor.getMarsPhotosArrayList(this);
    }

    @Override
    public void onFinished(ArrayList<MarsPhotos> marsPhotosArrayList) {
        if(this.mainView != null){
            mainView.setDataToRecyclerView(marsPhotosArrayList);
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
}
