package com.ben.etsyclient.view.search_result_view;

import com.ben.etsyclient.model.goods.Goods;
import com.ben.etsyclient.util.MadLog;

public class ResultSearchPresenterImpl implements ResultSearchPresenter {
    @Override
    public void attachView(ResultSearchView mvpView) {
        MadLog.log(this, "attachView");
    }

    @Override
    public void detachView() {
        MadLog.log(this, "detachView");
    }

    @Override
    public void showDetail(Goods goods) {
        MadLog.log(this, "showDetail");
    }
}
