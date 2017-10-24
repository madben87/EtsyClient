package com.ben.etsyclient.view.search_result_view;

import com.ben.etsyclient.data.DataManager;
import com.ben.etsyclient.data.Repository;
import com.ben.etsyclient.model.goods.Goods;
import com.ben.etsyclient.model.goods.GoodsList;
import com.ben.etsyclient.util.Constants;
import com.ben.etsyclient.util.MadLog;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observer;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

@Singleton
public class ResultSearchPresenterImpl implements ResultSearchPresenter, Constants {

    public Repository dataManager;
    private CompositeSubscription compositeSubscription;
    private ResultSearchView view;

    @Inject
    public ResultSearchPresenterImpl(DataManager dataManager) {this.dataManager = dataManager;}

    @Override
    public void showDetail(Goods goods) {
        MadLog.log(this, "showDetail");
    }

    @Override
    public void refreshList(String category, String keywords) {
        Subscription subscription = dataManager.syncItems(category, keywords)
                .subscribe(new Observer<GoodsList>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(GoodsList goodsList) {
                        view.showResult(goodsList);
                    }
                });
        compositeSubscription.add(subscription);
    }

    @Override
    public void attachView(ResultSearchView mvpView) {
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
