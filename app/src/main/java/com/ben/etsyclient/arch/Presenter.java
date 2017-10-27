package com.ben.etsyclient.arch;

public interface Presenter<V extends MVPView> {
    void attachView(V mvpView);
    void detachView();
}
