package com.ben.etsyclient.view.saved;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.ben.etsyclient.EtsyClientApplication;
import com.ben.etsyclient.R;
import com.ben.etsyclient.view.custom.JazzBallTextView;
import com.ben.etsyclient.data.model.goods.Goods;
import com.ben.etsyclient.util.Constants;
import com.nostra13.universalimageloader.core.ImageLoader;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class DeleteItemDialog extends DialogFragment implements Constants {

    @BindView(R.id.delete_dialog_card)
    CardView cardView;
    @BindView(R.id.delete_dialog_title)
    JazzBallTextView title;
    @BindView(R.id.delete_dialog_image)
    ImageView image;

    private Unbinder unbinder;
    private Goods goods;

    @Inject
    SavedPresenterImpl savedPresenter;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.delete_item_dialog, null);

        unbinder = ButterKnife.bind(this, view);

        EtsyClientApplication.getInjector().inject(this);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            this.goods = bundle.getParcelable(GOODS_KEY);
        }

        if (goods != null) {

            title.setText(goods.getTitle());
            ImageLoader.getInstance().displayImage(goods.getMainImage().getUrl_570xN(), image);

        }else {
            Toast.makeText(getContext(), "Error show dialog", Toast.LENGTH_SHORT).show();
            return null;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setView(view);

        return builder.create();
    }

    @OnClick({R.id.delete_dialog_btnCancel, R.id.delete_dialog_btnDelete})
    void click(View view) {
        switch (view.getId()) {
            case R.id.delete_dialog_btnCancel:
                dismiss();
                break;
            case R.id.delete_dialog_btnDelete:
                savedPresenter.deleteItem(goods.getListingId());
                dismiss();
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
