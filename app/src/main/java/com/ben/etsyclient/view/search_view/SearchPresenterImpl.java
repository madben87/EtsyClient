package com.ben.etsyclient.view.search_view;

import android.util.Log;

import com.ben.etsyclient.data.DataManager;
import com.ben.etsyclient.model.category.Categories;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observer;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;

@Singleton
public class SearchPresenterImpl implements SearchPresenter<SearchView> {

    private SearchView view;
    public DataManager dataManager;
    private Subscription subscription;

    @Inject
    public SearchPresenterImpl(DataManager dataManager) {
        this.dataManager = dataManager;
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
}
