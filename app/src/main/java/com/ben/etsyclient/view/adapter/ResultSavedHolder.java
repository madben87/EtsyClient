package com.ben.etsyclient.view.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.ben.etsyclient.R;
import com.ben.etsyclient.custom_view.JazzBallTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResultSavedHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

    @BindView(R.id.card_view_res_item)
    CardView cardViewResItem;
    @BindView(R.id.title_res_item)
    JazzBallTextView titleResItem;
    @BindView(R.id.price_res_item)
    JazzBallTextView priceResItem;
    @BindView(R.id.currency_code_res_item)
    JazzBallTextView currencyCodeResItem;
    @BindView(R.id.main_image_res_item)
    ImageView mainImageResItem;
    @BindView(R.id.delete_item)
    FrameLayout deleteItem;

    private ItemClick listener;
    private LongItemClick longItemClick;


    public ResultSavedHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);

        cardViewResItem.setPreventCornerOverlap(false);
        cardViewResItem.setOnClickListener(this);
        cardViewResItem.setOnLongClickListener(this);
    }

    @Override
    public void onClick(View v) {

        listener.onItemClick(v, this.getLayoutPosition());
    }

    @Override
    public boolean onLongClick(View view) {
        longItemClick.onLongItemClick(view, this.getLayoutPosition());
        return true;
    }

    public void setOnItemClickListener(ItemClick listener) {
        this.listener = listener;
    }

    public void setOnLongItemClickListener(LongItemClick longItemClick) {this.longItemClick = longItemClick;}
}
