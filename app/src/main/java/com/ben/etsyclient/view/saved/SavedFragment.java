package com.ben.etsyclient.view.saved;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ben.etsyclient.EtsyClientApplication;
import com.ben.etsyclient.R;
import com.ben.etsyclient.data.model.goods.GoodsList;
import com.ben.etsyclient.util.MadLog;
import com.ben.etsyclient.view.adapter.ResultSavedAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SavedFragment extends Fragment implements SavedView {

    @BindView(R.id.recyclerSavedGoods)
    RecyclerView recyclerView;

    @Inject
    ResultSavedAdapter resultSearchAdapter;
    @Inject
    SavedPresenterImpl savedPresenter;

    private Unbinder unbinder;
    private FragmentActivity fragmentActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_saved, container, false);

        EtsyClientApplication.getInjector().inject(this);

        unbinder = ButterKnife.bind(this, view);

        savedPresenter.attachView(this);

        savedPresenter.getItems();

        return view;
    }

    @Override
    public void showItems(GoodsList goodsList) {
        resultSearchAdapter.setGoods(goodsList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(resultSearchAdapter);
    }

    @Override
    public void showDialog(DialogFragment dialog) {
        dialog.show(fragmentActivity.getFragmentManager(), "DeleteItemDialog");
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAttach(Context context) {
        fragmentActivity = (FragmentActivity) context;
        super.onAttach(context);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        unbinder.unbind();

        MadLog.log(this, "onDestroyView");
    }
}
