package vn.com.mobifone.mfchatbot.view;

import java.util.ArrayList;

import vn.com.mobifone.mfchatbot.model.MarsPhotos;

/**
 * Created by Lê Nguyên Trà on 18/11/2021.
 * Copyright © 2021 VNPT IT 3. All rights reserved.
 */
public interface MainContract {

    interface Presenter{

        void onDestroy();

        void requestDataFromServer();

    }

    interface MainView {

        void showProgress();

        void hideProgress();

        void setDataToRecyclerView(ArrayList<MarsPhotos> noticeArrayList);

        void onResponseFailure(Throwable throwable);

    }

    /**
     * This is for fetching data from data source.
     **/
    interface Intractor {

        interface OnFinishedListener {
            void onFinished(ArrayList<MarsPhotos> marsPhotosArrayList);
            void onFailure(Throwable t);
        }

        void getMarsPhotosArrayList(OnFinishedListener onFinishedListener);
    }
}
