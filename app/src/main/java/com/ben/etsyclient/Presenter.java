package com.ben.etsyclient;

public interface Presenter<V extends MVPView> {

    void attachView(V mvpView);
    void detachView();
}
