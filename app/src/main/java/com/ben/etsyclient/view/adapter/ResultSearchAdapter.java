package com.ben.etsyclient.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ben.etsyclient.R;
import com.ben.etsyclient.model.item.GoodsList;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ResultSearchAdapter extends RecyclerView.Adapter<ResultSearchHolder> {

    private GoodsList goodsList;
    @Inject
    public Context context;

    private ImageLoader imageLoader;

    public void setGoods(GoodsList goodsList) {
        this.goodsList = goodsList;
    }

    @Inject
    public ResultSearchAdapter() {
        this.goodsList = new GoodsList();
    }

    @Override
    public ResultSearchHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.result_search_item, parent, false);
        imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(context));
        return new ResultSearchHolder(view);
    }

    @Override
    public void onBindViewHolder(final ResultSearchHolder holder, int position) {

        /*holder.textHeadLine.setText(String.valueOf(model.getResults().get(position).getTitle()));
        holder.textCaption.setText(String.valueOf(model.getResults().get(position).getAnnouncement()));
        holder.textTags.setText(String.valueOf(model.getResults().get(position).getTags()));

        if (model.getResults() != null) {
            Picasso.with(context).load(Uri.parse(model.getResults().get(position).getImgBig2x())).into(holder.image_news);
        }*/

        holder.titleResItem.setText(goodsList.getResults().get(position).getTitle());
        holder.priceResItem.setText(String.valueOf(goodsList.getResults().get(position).getPrice()));
        holder.currencyCodeResItem.setText(goodsList.getResults().get(position).getCurrencyCode());

        //imageLoader.displayImage(goodsList.getResults().get(position).getMainImage().getUrl_170x135(), holder.mainImageResItem);

        holder.setOnItemClickListener(new ItemClick() {
            @Override
            public void onItemClick(View view, int position) {

                /*switch (view.getId()) {
                    case R.id.card_recview:
                        CustomTab.tabShow(newsActivity, Uri.parse(model.getResults().get(position).getUrl()));
                        break;
                    case R.id.btn_open_detail:
                        holder.textCaption.setVisibility(View.VISIBLE);
                        holder.btnOpenDetail.setVisibility(View.GONE);
                        holder.btnCloseDetail.setVisibility(View.VISIBLE);
                        break;
                    case R.id.btn_close_detail:
                        holder.textCaption.setVisibility(View.GONE);
                        holder.btnOpenDetail.setVisibility(View.VISIBLE);
                        holder.btnCloseDetail.setVisibility(View.GONE);
                        break;
                }*/
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
