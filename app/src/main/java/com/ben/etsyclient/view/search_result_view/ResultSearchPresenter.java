package com.ben.etsyclient.view.search_result_view;

import android.support.v7.widget.RecyclerView;

import com.ben.etsyclient.Presenter;
import com.ben.etsyclient.model.goods.GoodsList;

public interface ResultSearchPresenter extends Presenter<ResultSearchView> {

    //Обновляет список товаров
    void refreshList(String category, String keywords);

    //Включаем Pagination
    void setPagination(RecyclerView recyclerView, final GoodsList goodsList);
}
