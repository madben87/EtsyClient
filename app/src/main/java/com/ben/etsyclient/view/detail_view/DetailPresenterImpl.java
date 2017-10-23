package com.ben.etsyclient.view.detail_view;

import com.ben.etsyclient.model.goods.Goods;
import com.ben.etsyclient.util.MadLog;

import javax.inject.Inject;

public class DetailPresenterImpl implements DetailPresenter {

    private DetailView detailView;

    @Inject
    public DetailPresenterImpl() {}

    @Override
    public void saveItem(Goods goods) {
        MadLog.log(this, "saveItem");
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
