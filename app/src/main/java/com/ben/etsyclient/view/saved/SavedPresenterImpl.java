package com.ben.etsyclient.view.saved;

import android.app.DialogFragment;
import android.os.Bundle;

import com.ben.etsyclient.data.DataManager;
import com.ben.etsyclient.data.Repository;
import com.ben.etsyclient.data.model.MessageEvent;
import com.ben.etsyclient.data.model.goods.Goods;
import com.ben.etsyclient.data.model.goods.GoodsList;
import com.ben.etsyclient.util.Constants;
import com.ben.etsyclient.util.EventConstants;
import com.ben.etsyclient.util.MadLog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observer;
import rx.subscriptions.CompositeSubscription;

@Singleton
public class SavedPresenterImpl implements SavedPresenter, EventConstants, Constants {

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
    public void deleteItem(long id) {

        long result = dataManager.deleteItem(id);

        if (result >= 0) {
            getItems();
            MadLog.log(this, "deleteItem");
        }else {
            view.showMessage("Error delete");
        }
    }

    @Override
    public void showDialog(Goods goods) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(GOODS_KEY, goods);

        DialogFragment dialog = new DeleteItemDialog();

        dialog.setArguments(bundle);

        this.view.showDialog(dialog);
    }

    @Override
    public void attachView(SavedView mvpView) {
        this.view = mvpView;
        compositeSubscription = new CompositeSubscription();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        MadLog.log(this, "attachView");
    }

    @Override
    public void detachView() {
        view = null;
        unSubscribe();
        EventBus.getDefault().unregister(this);
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MessageEvent event){

        switch (event.message) {
            case EVENT_REFRESH:

                getItems();
                break;
        }
    }
}
