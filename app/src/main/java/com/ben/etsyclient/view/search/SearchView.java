package com.ben.etsyclient.view.search;

import com.ben.etsyclient.arch.MVPView;
import com.ben.etsyclient.data.model.category.Categories;
import com.ben.etsyclient.data.model.goods.GoodsList;

public interface SearchView extends MVPView {

    //При старте главного экрана фрагмент вызывает метод SearchPresenter.getCategories
    //Этот метод отображает данные во вью
    void showCategories(Categories categories);

    void showResult(GoodsList goodsList);
}
