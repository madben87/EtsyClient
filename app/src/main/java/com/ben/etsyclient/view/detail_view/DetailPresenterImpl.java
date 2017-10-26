package com.ben.etsyclient.view.detail_view;

import android.content.Context;
import android.widget.Toast;

import com.ben.etsyclient.data.DataManager;
import com.ben.etsyclient.data.Repository;
import com.ben.etsyclient.model.goods.Goods;
import com.ben.etsyclient.util.MadLog;

import javax.inject.Inject;

public class DetailPresenterImpl implements DetailPresenter {

    private DetailView detailView;
    private Repository dataManager;
    private Context context;

    @Inject
    public DetailPresenterImpl(DataManager dataManager, Context context) {
        this.dataManager = dataManager;
        this.context = context;
    }

    @Override
    public void saveGoods(Goods goods) {

        long result = dataManager.saveItem(goods);

        if (result >= 0) {
            Toast.makeText(context, "Goods is saved", Toast.LENGTH_SHORT).show();
            MadLog.log(this, "saveGoods");
        }else {
            Toast.makeText(context, "Save error", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void deleteItem(long id) {

        long result = dataManager.deleteItem(id);

        if (result >= 0) {
            Toast.makeText(context, "Goods is deleted", Toast.LENGTH_SHORT).show();
            MadLog.log(this, "deleteItem");
        }else {
            Toast.makeText(context, "Delete error", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void startView(Goods goods) {
        if (goods != null) {
            detailView.showDetail(goods);

            MadLog.log(this, "startView");
        }
    }

    @Override
    public void attachView(DetailView mvpView) {
        this.detailView = mvpView;

        MadLog.log(this, "attachView");
    }

    @Override
    public void detachView() {
        detailView = null;

        MadLog.log(this, "detachView");
    }
}
