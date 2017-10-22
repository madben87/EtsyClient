package com.ben.etsyclient.view.detail_view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ben.etsyclient.R;
import com.ben.etsyclient.model.goods.Goods;

public class DetailActivity extends AppCompatActivity implements DetailView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
    }

    @Override
    public void showDetail(Goods goods) {

    }
}
