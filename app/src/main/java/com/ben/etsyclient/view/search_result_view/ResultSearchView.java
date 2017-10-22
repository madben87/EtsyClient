package com.ben.etsyclient.view.search_result_view;

import com.ben.etsyclient.MVPView;
import com.ben.etsyclient.model.goods.GoodsList;

public interface ResultSearchView extends MVPView {

    //Этот метод отображает данные поиска во вью
    void showResult(GoodsList goodsList);
}
