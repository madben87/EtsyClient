package com.ben.etsyclient.view.search_view;

import com.ben.etsyclient.Presenter;

public interface SearchPresenter extends Presenter<SearchView> {

    //Вызывает метод Repository.syncCategories для загрузки данных с сервера, и в случае ошибки вызывает
    //метод Repository.getCategories для загрузки данных из БД, после чего вызывает метод SearchView.showCategories
    void syncCategories();
    //После заполнения формы поиска и нажатии кнопки в SearchView этот метод вызывает метод
    //Repository.syncItems и возвращает результат в метод ResultSearchView.showResult
    void searchItems(String category, String keywords);
}
