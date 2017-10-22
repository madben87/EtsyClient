package com.ben.etsyclient.view.search_result_view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ben.etsyclient.EtsyClient;
import com.ben.etsyclient.R;
import com.ben.etsyclient.model.goods.GoodsList;
import com.ben.etsyclient.view.adapter.ResultSearchAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResultSearchActivity extends AppCompatActivity implements ResultSearchView {

    @BindView(R.id.res_search_list)
    RecyclerView resSearchList;

    @Inject
    ResultSearchAdapter resultSearchAdapter;

    private GoodsList goodsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_search);

        EtsyClient.getInjector().inject(this);

        ButterKnife.bind(this);

        goodsList = getIntent().getParcelableExtra("GoodsList");

        resultSearchAdapter.setGoods(goodsList);
        resSearchList.setHasFixedSize(true);
        resSearchList.setLayoutManager(new GridLayoutManager(this, 2));
        resSearchList.setAdapter(resultSearchAdapter);
    }

    @Override
    public void showResult(GoodsList goodsList) {

    }
}
