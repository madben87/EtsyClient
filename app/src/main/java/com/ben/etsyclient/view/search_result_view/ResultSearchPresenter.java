package com.ben.etsyclient.view.search_result_view;

import com.ben.etsyclient.Presenter;
import com.ben.etsyclient.model.goods.Goods;

public interface ResultSearchPresenter extends Presenter<ResultSearchView> {

    //При нажатии на элемент списка с найденными результатами вызывает метод DetailView.showDetail
    void showDetail(Goods goods);

    //Обновляет список товаров
    void refreshList(String category, String keywords);
}
