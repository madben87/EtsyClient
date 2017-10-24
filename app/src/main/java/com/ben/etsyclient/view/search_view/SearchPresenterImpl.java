package com.ben.etsyclient.view.search_view;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.ben.etsyclient.data.DataManager;
import com.ben.etsyclient.data.Repository;
import com.ben.etsyclient.model.category.Categories;
import com.ben.etsyclient.model.goods.GoodsList;
import com.ben.etsyclient.util.Constants;
import com.ben.etsyclient.util.MadLog;
import com.ben.etsyclient.view.search_result_view.ResultSearchActivity;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observer;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

@Singleton
public class SearchPresenterImpl implements SearchPresenter, Constants {

    private SearchView view;
    public Repository dataManager;
    private CompositeSubscription compositeSubscription;
    private Context context;

    @Inject
    public SearchPresenterImpl(DataManager dataManager, Context context) {
        this.dataManager = dataManager;
        this.context = context;
    }

    @Override
    public void syncCategories() {

        Subscription subscription = dataManager.syncCategories()
                    .subscribe(new Observer<Categories>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.d(">>>>>>>>>>>>>>>>>>>>>>>", e.getMessage());
                            Subscription subscription = dataManager.getCategories()
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
                            compositeSubscription.add(subscription);
                        }

                        @Override
                        public void onNext(Categories categories) {
                            view.showCategories(categories);
                        }
                    });

        compositeSubscription.add(subscription);
    }

    @Override
    public void searchItems(final String category, final String keywords) {

        Subscription subscription = dataManager.syncItems(category, keywords)
                .subscribe(new Observer<GoodsList>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        MadLog.error(this, e.getMessage());
                    }

                    @Override
                    public void onNext(GoodsList goodsList) {
                        Intent intent = new Intent(context, ResultSearchActivity.class);
                        intent.putExtra(GOODS_LIST_KEY, goodsList);
                        //intent.putExtra(CATEGORY, category);
                        //intent.putExtra(KEYWORD, keywords);
                        context.startActivity(intent);
                    }
                });
        compositeSubscription.add(subscription);
    }

    @Override
    public void attachView(SearchView mvpView) {
        view = mvpView;
        compositeSubscription = new CompositeSubscription();
        MadLog.log(this, "attachView");
    }

    @Override
    public void detachView() {
        view = null;
        unSubscribe();
        MadLog.log(this, "detachView");
    }

    public void unSubscribe() {
        if (compositeSubscription != null) {
            if (!compositeSubscription.isUnsubscribed()) {
                compositeSubscription.unsubscribe();
                compositeSubscription = null;
            }
        }
    }
}
