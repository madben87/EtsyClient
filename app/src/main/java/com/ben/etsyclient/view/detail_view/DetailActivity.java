package com.ben.etsyclient.view.detail_view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.ben.etsyclient.EtsyClient;
import com.ben.etsyclient.R;
import com.ben.etsyclient.custom_view.JazzBallTextView;
import com.ben.etsyclient.model.goods.Goods;
import com.ben.etsyclient.util.Constants;
import com.ben.etsyclient.util.MadLog;
import com.ben.etsyclient.view.search_view.SearchPresenterImpl;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailActivity extends AppCompatActivity implements DetailView, Constants {

    @BindView(R.id.detail_price)
    JazzBallTextView detailPrice;
    @BindView(R.id.detail_currency_code)
    JazzBallTextView detailCurrencyCode;
    @BindView(R.id.detail_title)
    JazzBallTextView detailTitle;
    @BindView(R.id.detail_description)
    JazzBallTextView detailDescription;
    @BindView(R.id.detail_img)
    ImageView detailImg;
    @BindView(R.id.detail_btn)
    FrameLayout detailBtn;

    private Goods goods;

    private ImageLoader imageLoader;

    @Inject
    DetailPresenterImpl detailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        /*if (getIntent() != null) {
            if (getIntent().getStringExtra(SAVED_FLAG) != null && getIntent().getStringExtra(SAVED_FLAG).equals(SAVED_FLAG)) {
                detailBtn.setVisibility(View.INVISIBLE);
            }
        }*/

        ButterKnife.bind(this);

        EtsyClient.getInjector().inject(this);

        imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(this));

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(GOODS_KEY)) {
                this.goods = (Goods) savedInstanceState.get(GOODS_KEY);
            }
        }

        detailPresenter.attachView(this);
        detailPresenter.startView((Goods) getIntent().getParcelableExtra(GOODS_KEY));

        MadLog.log(this, "onCreate");
    }

    @OnClick(R.id.detail_btn)
    void click(View view) {
        detailPresenter.saveGoods(goods);
    }

    @Override
    public void showDetail(Goods goods) {

        this.goods = goods;

        detailPrice.setText(String.valueOf(goods.getPrice()));
        detailCurrencyCode.setText(goods.getCurrencyCode());
        detailTitle.setText(goods.getTitle());
        detailDescription.setText(goods.getDescription());

        imageLoader.displayImage(goods.getMainImage().getUrl_fullxfull(), detailImg);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelable(GOODS_KEY, goods);
    }
}
