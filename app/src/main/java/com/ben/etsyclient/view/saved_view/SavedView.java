package com.ben.etsyclient.view.saved_view;

import com.ben.etsyclient.MVPView;
import com.ben.etsyclient.model.goods.GoodsList;

public interface SavedView extends MVPView {

    //При старте главного экрана фрагмент вызывает метод SavePresenter.getItems
    //Этот метод отображает данные во вью
    void showItems(GoodsList goodsList);
}
