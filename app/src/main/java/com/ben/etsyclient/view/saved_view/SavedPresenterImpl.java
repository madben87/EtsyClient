package com.ben.etsyclient.view.saved_view;

import com.ben.etsyclient.data.DataManager;
import com.ben.etsyclient.data.Repository;
import com.ben.etsyclient.model.goods.Goods;
import com.ben.etsyclient.model.goods.GoodsList;
import com.ben.etsyclient.util.MadLog;

import javax.inject.Inject;

import rx.Observer;
import rx.subscriptions.CompositeSubscription;

public class SavedPresenterImpl implements SavedPresenter {

    private Repository dataManager;
    private CompositeSubscription compositeSubscription;
    private SavedView view;

    @Inject
    public SavedPresenterImpl(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void getItems() {
        compositeSubscription.add(dataManager.getItems()
        .subscribe(new Observer<GoodsList>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                MadLog.log(this, e.getMessage());
            }

            @Override
            public void onNext(GoodsList goodsList) {
                view.showItems(goodsList);
            }
        }));
    }

    @Override
    public void deleteItem(Goods goods) {

    }

    @Override
    public void showItemDetail(int position) {

    }

    @Override
    public void attachView(SavedView mvpView) {
        this.view = mvpView;
        compositeSubscription = new CompositeSubscription();
        MadLog.log(this, "attachView");
    }

    @Override
    public void detachView() {
        view = null;
        unSubscribe();
        MadLog.log(this, "detachView");
    }

    private void unSubscribe() {
        if (compositeSubscription != null) {
            if (!compositeSubscription.isUnsubscribed()) {
                compositeSubscription.unsubscribe();
                compositeSubscription = null;
            }
        }
    }
}
