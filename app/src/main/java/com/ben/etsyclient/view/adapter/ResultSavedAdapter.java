package com.ben.etsyclient.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ben.etsyclient.R;
import com.ben.etsyclient.model.goods.Goods;
import com.ben.etsyclient.model.goods.GoodsList;
import com.ben.etsyclient.util.Constants;
import com.ben.etsyclient.util.MadLog;
import com.ben.etsyclient.view.detail_view.DetailActivity;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ResultSavedAdapter extends RecyclerView.Adapter<ResultSavedHolder> implements Constants {

    private GoodsList goodsList;

    @Inject
    public Context context;

    @Inject
    public ResultSavedAdapter() {
        this.goodsList = new GoodsList();
    }

    public void setGoods(GoodsList goodsList) {
        this.goodsList = goodsList;
    }

    @Override
    public ResultSavedHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.result_search_item, parent, false);
        return new ResultSavedHolder(view);
    }

    @Override
    public void onBindViewHolder(final ResultSavedHolder holder, int position) {

        holder.titleResItem.setText(goodsList.getResults().get(position).getTitle());
        holder.priceResItem.setText(String.valueOf(goodsList.getResults().get(position).getPrice()));
        holder.currencyCodeResItem.setText(goodsList.getResults().get(position).getCurrencyCode());

        holder.mainImageResItem.setImageResource(0);

        ImageLoader.getInstance().displayImage(goodsList.getResults().get(position).getMainImage().getUrl_570xN(), holder.mainImageResItem);

        MadLog.log(this, String.valueOf(ImageLoader.getInstance().getMemoryCache()));

        holder.setOnItemClickListener(new ItemClick() {
            @Override
            public void onItemClick(View view, int position) {

                switch (view.getId()) {
                    case R.id.card_view_res_item:
                        Intent intent = new Intent(context, DetailActivity.class);
                        intent.putExtra(GOODS_KEY, goodsList.getResults().get(position));
                        intent.putExtra(SAVED_FLAG, SAVED_FLAG);
                        context.startActivity(intent);
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (goodsList.getResults() != null) {
            return goodsList.getResults().size();
        }
        return 0;
    }
}
