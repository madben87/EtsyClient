package com.ben.etsyclient.view.search_view;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.ben.etsyclient.data.DataManager;
import com.ben.etsyclient.data.Repository;
import com.ben.etsyclient.model.category.Categories;
import com.ben.etsyclient.model.goods.GoodsList;
import com.ben.etsyclient.view.search_result_view.ResultSearchActivity;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observer;
import rx.Subscription;

@Singleton
public class SearchPresenterImpl implements SearchPresenter {

    private SearchView view;
    public Repository dataManager;
    private Subscription subscription;
    private Context context;

    @Inject
    public SearchPresenterImpl(DataManager dataManager, Context context) {
        this.dataManager = dataManager;
        this.context = context;
    }

    @Override
    public void attachView(SearchView mvpView) {
        view = mvpView;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void syncCategories() {

            subscription = dataManager.syncCategories()
                    .subscribe(new Observer<Categories>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.d(">>>>>>>>>>>>>>>>>>>>>>>", e.getMessage());
                            subscription = dataManager.getCategories()
                                    .subscribe(new Observer<Categories>() {
                                        @Override
                                        public void onCompleted() {

                                        }

                                        @Override
                                        public void onError(Throwable e) {
                                            Log.d(">>>>>>>>>>>>>>>>>>>>>>>", e.getMessage());
                                        }

                                        @Override
                                        public void onNext(Categories categories) {
                                            view.showCategories(categories);
                                        }
                                    });
                        }

                        @Override
                        public void onNext(Categories categories) {
                            view.showCategories(categories);
                        }
                    });
    }

    @Override
    public void searchItems(String category, String keywords) {

        subscription = dataManager.syncItems(category, keywords)
                .subscribe(new Observer<GoodsList>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(GoodsList goodsList) {
                        Intent intent = new Intent(context, ResultSearchActivity.class);
                        intent.putExtra("GoodsList", goodsList);
                        context.startActivity(intent);
                    }
                });
    }
}
