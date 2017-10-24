package com.ben.etsyclient.view.adapter.pagination;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.ben.etsyclient.R;
import com.ben.etsyclient.custom_view.JazzBallTextView;
import com.ben.etsyclient.view.adapter.ItemClick;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResultSearchHolderPagination extends RecyclerView.ViewHolder implements View.OnClickListener {

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

    private ItemClick listener;


    public ResultSearchHolderPagination(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);

        cardViewResItem.setPreventCornerOverlap(false);
        cardViewResItem.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        listener.onItemClick(v, this.getLayoutPosition());
    }

    public void setOnItemClickListener(ItemClick listener) {
        this.listener = listener;
    }
}
