package com.ben.etsyclient.data;

import android.util.Log;

import com.ben.etsyclient.EtsyClient;
import com.ben.etsyclient.data.database.CategoryDaoImpl;
import com.ben.etsyclient.model.category.Categories;
import com.ben.etsyclient.model.goods.Goods;
import com.ben.etsyclient.model.goods.GoodsList;
import com.ben.etsyclient.util.Constants;
import com.ben.etsyclient.util.MadLog;

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
        MadLog.log(this, "syncCategories");
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
        MadLog.log(this, "getCategories");
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
        MadLog.log(this, "syncItems");
        return retrofitService.getItems(APP_KEY, category, keywords, MAIN_IMAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<GoodsList> loadNextItems(String category, String keywords, int limit, int offset) {
        MadLog.log(this, "loadNextItems");
        return retrofitService.getNextItems(APP_KEY, category, keywords, MAIN_IMAGE, limit, offset)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<GoodsList> getItems() {
        MadLog.log(this, "getItems");
        return null;
    }

    @Override
    public void saveItem(Goods goods) {
        MadLog.log(this, "saveItem");
    }

    @Override
    public void deleteItem(Goods goods) {
        MadLog.log(this, "deleteItem");
    }
}
