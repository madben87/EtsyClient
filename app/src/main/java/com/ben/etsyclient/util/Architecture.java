package com.ben.etsyclient.util;

import com.ben.etsyclient.model.category.Categories;
import com.ben.etsyclient.model.goods.Goods;
import com.ben.etsyclient.model.goods.GoodsList;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

interface SearchView {
    //При старте главного экрана фрагмент вызывает метод SearchPresenter.getCategories
    //Этот метод отображает данные во вью
    void showCategories(Categories categories);
}

interface SearchPresenter extends Presenter {
    //Вызывает метод Repository.syncCategories для загрузки данных с сервера, и в случае ошибки вызывает
    //метод Repository.getCategories для загрузки данных из БД, после чего вызывает метод SearchView.showCategories
    Categories getCategories();
    //После заполнения формы поиска и нажатии кнопки в SearchView этот метод вызывает метод
    //Repository.syncItems и возвращает результат в метод ResultSearchView.showResult
    GoodsList searchItems(String category, String keywords);
}

interface SaveView {
    //При старте главного экрана фрагмент вызывает метод SavePresenter.getItems
    //Этот метод отображает данные во вью
    void showItems(GoodsList goodsList);
}

interface SavePresenter extends Presenter {
    //Вызывает метод Repository.getItems для загрузки данных из БД и метод SaveView.showItems для отображения
    GoodsList getItems();
    //Вызывает метод Repository.deleteItem для удаления элемента из БД
    void deleteItem(Goods goods);
    //Вызывает метод DetailView.showDetail для отображения информации
    void showItemDetail(int position);
}

interface ResultSearchView {
    //Этот метод отображает данные поиска во вью
    void showResult(GoodsList goodsList);
}

interface ResultSearchPresenter extends Presenter {
    //При нажатии на элемент списка с найденными результатами вызывает метод DetailView.showDetail
    void showDetail(Goods goods);
}

interface DetailView {
    //Этот метод отображает данные во вью
    void showDetail(Goods goods);
}

interface DetailPresenter extends Presenter {
    //При нажатии на кнопку "сохранить" вызывает метод Repository.saveItem для сохранения элемента в БД
    void saveItem(Goods goods);
}

interface Presenter {
    //
    void attache(DetailView detailView);
    //
    void detach();
}

interface Repository {
    //Загружает список категорий с сервера вызывая метод RetrofitService.getCategories
    Observable<Categories> syncCategories(String apiKey);
    //Загружает список категорий из БД
    Observable<Categories> getCategories();
    //Загружает список товаров с сервера вызывая метод RetrofitService.getItems
    Observable<GoodsList> syncItems(String apiKey, String category, String keywords);
    //Загружает список товаров из БД
    Observable<GoodsList> getItems();
    //Сохраняет товар в БД
    void saveItem(Goods goods);
    //Удаляет товар из БД
    void deleteItem(Goods goods);
}

interface RetrofitService {
    @GET("v2/taxonomy/categories")
    Observable<Categories> getCategories(@Query("api_key") String apiKey);
    @GET("v2/listings/active")
    Observable<GoodsList> getItems(@Query("api_key") String apiKey, @Query("category") String category, @Query("keywords") String keywords);
}