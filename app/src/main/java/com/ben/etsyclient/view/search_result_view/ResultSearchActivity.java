package com.ben.etsyclient.view.search_result_view;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ben.etsyclient.EtsyClient;
import com.ben.etsyclient.R;
import com.ben.etsyclient.model.goods.GoodsList;
import com.ben.etsyclient.util.Constants;
import com.ben.etsyclient.util.MadLog;
import com.ben.etsyclient.view.adapter.ResultSearchAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResultSearchActivity extends AppCompatActivity implements ResultSearchView, SwipeRefreshLayout.OnRefreshListener, Constants {

    @BindView(R.id.res_search_list)
    RecyclerView resSearchList;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    @Inject
    ResultSearchAdapter resultSearchAdapter;
    @Inject
    ResultSearchPresenterImpl resultSearchPresenter;

    private GoodsList goodsList;
    private String category;
    private String keyword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_search);

        EtsyClient.getInjector().inject(this);

        ButterKnife.bind(this);

        resultSearchPresenter.attachView(this);

        if (getIntent() != null) {

            goodsList = getIntent().getParcelableExtra(GOODS_LIST_KEY);

            category = getIntent().getStringExtra(CATEGORY);
            keyword = getIntent().getStringExtra(KEYWORD);
        }

        if (goodsList != null) {
            showResult(goodsList);
        }

        swipeRefreshLayout.setOnRefreshListener(this);

        MadLog.log(this, "onCreate");
    }

    @Override
    public void showResult(GoodsList goodsList) {

        resultSearchAdapter.setGoods(goodsList);
        resSearchList.setHasFixedSize(true);
        resSearchList.setLayoutManager(new GridLayoutManager(this, 2));
        resSearchList.setAdapter(resultSearchAdapter);

        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }

        MadLog.log(this, "showResult");
    }

    @Override
    public void onRefresh() {
        resultSearchPresenter.refreshList(category, keyword);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        resultSearchPresenter.detachView();
        MadLog.log(this, "onDestroy");
    }
}
