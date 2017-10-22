package com.ben.etsyclient.view.search_view;

import com.ben.etsyclient.MVPView;
import com.ben.etsyclient.model.category.Categories;

public interface SearchView extends MVPView {

    //При старте главного экрана фрагмент вызывает метод SearchPresenter.getCategories
    //Этот метод отображает данные во вью
    void showCategories(Categories categories);
}
