package com.ben.etsyclient.data;

import com.ben.etsyclient.data.model.category.Categories;
import com.ben.etsyclient.data.model.goods.Goods;
import com.ben.etsyclient.data.model.goods.GoodsList;

import rx.Observable;

public interface Repository {

    //Загружает список категорий с сервера вызывая метод RetrofitService.getCategories
    Observable<Categories> syncCategories();
    //Загружает список категорий из БД
    Observable<Categories> getCategories();
    //Загружает список товаров с сервера вызывая метод RetrofitService.getItems
    Observable<GoodsList> syncItems(String category, String keywords);
    //Pagination
    Observable<GoodsList> loadNextItems(String category, String keywords, int limit, int offset);
    //Загружает список товаров из БД
    Observable<GoodsList> getItems();
    //Сохраняет товар в БД
    long saveItem(Goods goods);
    //Удаляет товар из БД
    long deleteItem(long id);
}
