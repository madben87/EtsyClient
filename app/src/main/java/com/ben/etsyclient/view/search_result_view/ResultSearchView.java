package com.ben.etsyclient.view.search_result_view;

import com.ben.etsyclient.MVPView;
import com.ben.etsyclient.model.goods.Goods;
import com.ben.etsyclient.model.goods.GoodsList;

import java.util.ArrayList;

public interface ResultSearchView extends MVPView {

    //Этот метод отображает данные поиска во вью
    void showResult(GoodsList goodsList);

    void showNextPage(ArrayList<Goods> list);
}
