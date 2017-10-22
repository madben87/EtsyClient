package com.ben.etsyclient.view.search_view;

import com.ben.etsyclient.Presenter;
import com.ben.etsyclient.model.item.GoodsList;

public interface SearchPresenter extends Presenter<SearchView> {

    void syncCategories();
    void searchItems(String category, String keywords);
}
