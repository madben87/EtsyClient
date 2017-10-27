package com.ben.etsyclient.view.search;

import com.ben.etsyclient.data.DataManager;
import com.ben.etsyclient.data.Repository;
import com.ben.etsyclient.data.model.category.Categories;
import com.ben.etsyclient.data.model.goods.GoodsList;
import com.ben.etsyclient.util.Constants;
import com.ben.etsyclient.util.MadLog;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observer;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

@Singleton
public class SearchPresenterImpl implements SearchPresenter, Constants {

    private SearchView view;
    private Repository dataManager;
    private CompositeSubscription compositeSubscription;

    @Inject
    public SearchPresenterImpl(DataManager dataManager) {
        this.dataManager = dataManager;
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
                            MadLog.log(this, e.getMessage());
                            Subscription subscription = dataManager.getCategories()
                                    .subscribe(new Observer<Categories>() {
                                        @Override
                                        public void onCompleted() {

                                        }

                                        @Override
                                        public void onError(Throwable e) {
                                            MadLog.log(this, e.getMessage());
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
                        view.showResult(goodsList);
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
