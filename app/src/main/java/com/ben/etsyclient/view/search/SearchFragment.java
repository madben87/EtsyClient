package com.ben.etsyclient.view.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.ben.etsyclient.EtsyClientApplication;
import com.ben.etsyclient.R;
import com.ben.etsyclient.data.model.category.Categories;
import com.ben.etsyclient.data.model.category.Category;
import com.ben.etsyclient.data.model.goods.GoodsList;
import com.ben.etsyclient.util.Constants;
import com.ben.etsyclient.util.JazzBallFont;
import com.ben.etsyclient.util.MadLog;
import com.ben.etsyclient.view.result.ResultSearchActivity;

import java.util.ArrayList;
import java.util.Collections;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SearchFragment extends Fragment implements Constants, SearchView {

    @BindView(R.id.jazzBallTextView)
    EditText textView;
    @BindView(R.id.submit_btn)
    LinearLayout submitBtn;
    @BindView(R.id.spin_categories)
    Spinner spinnerCategories;
    @BindView(R.id.submit_logo)
    ImageView submitLogo;
    @BindView(R.id.progressBar_btn)
    ProgressBar progressBarBtn;

    private Unbinder unbinder;

    private ArrayList<String> categoryList;

    @Inject
    SearchPresenterImpl searchPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MadLog.log(this, "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search, container, false);

        unbinder = ButterKnife.bind(this, view);

        EtsyClientApplication.getInjector().inject(this);

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(SEARCH_INPUT)) {
                textView.setText((CharSequence) savedInstanceState.get(SEARCH_INPUT));
            }
        }

        textView.setTypeface(JazzBallFont.getTypeface());

        searchPresenter.attachView(this);

        searchPresenter.syncCategories();

        MadLog.log(this, "onCreateView");

        return view;
    }

    @OnClick(R.id.submit_btn)
    void click(View view) {
        if (spinnerCategories != null && spinnerCategories.getSelectedItem() != null && textView.getText() != null && !textView.getText().toString().equals("")) {
            searchPresenter.searchItems(categoryList.get(spinnerCategories.getSelectedItemPosition()), textView.getText().toString());
            MadLog.log(this, "click");
        }else {
            Toast.makeText(getContext(), "Please, enter search query, or choose category", Toast.LENGTH_SHORT).show();
        }
    }

    public void showResult(GoodsList goodsList) {
        Intent intent = new Intent(getContext(), ResultSearchActivity.class);
        intent.putExtra(GOODS_LIST_KEY, goodsList);
        intent.putExtra(FLAG, SEARCH_FLAG);
        startActivity(intent);
    }

    @Override
    public void showCategories(Categories categories) {

        categoryList = new ArrayList<>();

        for (Category elem : categories.getResults()) {
            categoryList.add(elem.getLongName());
        }

        Collections.sort(categoryList);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), R.layout.custom_spinner_item, categoryList);
        adapter.setDropDownViewResource(R.layout.custom_spinner_item);

        spinnerCategories.setAdapter(adapter);

        MadLog.log(this, "showCategories");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (textView.getText() != null) {
            outState.putString(SEARCH_INPUT, textView.getText().toString());
        }

        MadLog.log(this, "onSaveInstanceState");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        unbinder.unbind();

        MadLog.log(this, "onDestroyView");
    }
}
