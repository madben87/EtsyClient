package com.ben.etsyclient.view.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.ben.etsyclient.EtsyClientApplication;
import com.ben.etsyclient.view.main.MainActivity;
import com.ben.etsyclient.R;
import com.ben.etsyclient.view.custom.JazzBallTextView;
import com.ben.etsyclient.data.model.goods.Goods;
import com.ben.etsyclient.util.Constants;
import com.ben.etsyclient.util.MadLog;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

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
    @BindView(R.id.saveBtn)
    JazzBallTextView saveBtn;
    @BindView(R.id.deleteBtn)
    JazzBallTextView deleteBtn;

    private Goods goods;

    private ImageLoader imageLoader;

    private String LOCAL_FLAG;

    @Inject
    DetailPresenterImpl detailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);

        EtsyClientApplication.getInjector().inject(this);

        imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(this));

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(GOODS_KEY)) {
                this.goods = (Goods) savedInstanceState.get(GOODS_KEY);
            }
        }

        detailPresenter.attachView(this);
        detailPresenter.startView((Goods) getIntent().getParcelableExtra(GOODS_KEY));

        if (getIntent() != null) {
            if (getIntent().getStringExtra(FLAG) != null && getIntent().getStringExtra(FLAG).equals(SAVED_FLAG)) {
                saveBtn.setVisibility(View.GONE);
                deleteBtn.setVisibility(View.VISIBLE);
                LOCAL_FLAG = getIntent().getStringExtra(FLAG);
            }else if (getIntent().getStringExtra(FLAG) != null && getIntent().getStringExtra(FLAG).equals(SEARCH_FLAG)) {
                saveBtn.setVisibility(View.VISIBLE);
                deleteBtn.setVisibility(View.GONE);
                LOCAL_FLAG = getIntent().getStringExtra(FLAG);
            }
        }

        MadLog.log(this, "onCreate");
    }

    @OnClick({R.id.saveBtn, R.id.deleteBtn})
    void click(View view) {
        if (LOCAL_FLAG.equals(SEARCH_FLAG)) {
            detailPresenter.saveGoods(goods);
        }else if (LOCAL_FLAG.equals(SAVED_FLAG)) {
            detailPresenter.deleteItem(goods.getListingId());
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
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
