package com.ben.etsyclient.data;

import android.util.Log;

import com.ben.etsyclient.EtsyClient;
import com.ben.etsyclient.data.database.CategoryDaoImpl;
import com.ben.etsyclient.model.category.Categories;
import com.ben.etsyclient.model.item.Goods;
import com.ben.etsyclient.model.item.GoodsList;
import com.ben.etsyclient.util.Constants;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class DataManager implements Repository, Constants {

    private final RetrofitService retrofitService;

    @Inject
    public CategoryDaoImpl categoryDao;

    @Inject
    public DataManager(RetrofitService retrofitService) {
        this.retrofitService = retrofitService;
        EtsyClient.getInjector().inject(this);
    }

    @Override
    public Observable<Categories> syncCategories() {
        return retrofitService.getCategories(APP_KEY)
                .subscribeOn(Schedulers.io())
                .map(new Func1<Categories, Categories>() {
                    @Override
                    public Categories call(Categories categories) {
                        //Cached data
                        categoryDao.cachedCategory(categories);
                        return categories;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<Categories> getCategories() {
        Log.d("DataManager", ">>>>>>>>>>>>>>>>>>>>>>>>>>>getCategories");
        return Observable.defer(new Func0<Observable<Categories>>() {
            @Override
            public Observable<Categories> call() {
                return Observable.just(categoryDao.getCategories())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        });
    }

    @Override
    public Observable<GoodsList> syncItems(String category, String keywords) {
        return retrofitService.getItems(APP_KEY, category, keywords)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<GoodsList> getItems() {
        return null;
    }

    @Override
    public void saveItem(Goods goods) {

    }

    @Override
    public void deleteItem(Goods goods) {

    }
}
