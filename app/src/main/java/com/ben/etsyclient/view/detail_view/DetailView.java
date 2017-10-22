package com.ben.etsyclient.view.detail_view;

import com.ben.etsyclient.MVPView;
import com.ben.etsyclient.model.goods.Goods;

public interface DetailView extends MVPView {

    //Этот метод отображает данные во вью
    void showDetail(Goods goods);
}
