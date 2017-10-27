package com.ben.etsyclient.view.result;

import com.ben.etsyclient.arch.MVPView;
import com.ben.etsyclient.data.model.goods.Goods;
import com.ben.etsyclient.data.model.goods.GoodsList;

import java.util.ArrayList;

public interface ResultSearchView extends MVPView {

    //Этот метод отображает данные поиска во вью
    void showResult(GoodsList goodsList);

    //Этот метод отображает отображает следущую страницу
    void showNextPage(ArrayList<Goods> list);
}
