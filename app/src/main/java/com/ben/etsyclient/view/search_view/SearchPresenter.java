package com.ben.etsyclient.view.search_view;

public interface SearchPresenter<V extends SearchView> {

    void attachView(V mvpView);
    void detachView();
    void syncCategories();
}
