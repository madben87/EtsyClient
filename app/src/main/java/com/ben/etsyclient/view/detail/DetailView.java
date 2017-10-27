package com.ben.etsyclient.view.detail;

import com.ben.etsyclient.arch.MVPView;
import com.ben.etsyclient.data.model.goods.Goods;

public interface DetailView extends MVPView {

    //Этот метод отображает данные во вью
    void showDetail(Goods goods);
}
