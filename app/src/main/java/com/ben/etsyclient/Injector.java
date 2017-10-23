package com.ben.etsyclient;

import com.ben.etsyclient.custom_view.JazzBallTextView;
import com.ben.etsyclient.data.DataManager;
import com.ben.etsyclient.data.database.CategoryDaoImpl;
import com.ben.etsyclient.modules.ContextModule;
import com.ben.etsyclient.modules.RetrofitModule;
import com.ben.etsyclient.util.JazzBallFont;
import com.ben.etsyclient.view.detail_view.DetailActivity;
import com.ben.etsyclient.view.search_result_view.ResultSearchActivity;
import com.ben.etsyclient.view.search_view.SearchFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ContextModule.class, RetrofitModule.class})
public interface Injector {

    void inject(JazzBallTextView jazzBallTextView);
    void inject(JazzBallFont jazzBallFont);
    void inject(SearchFragment searchFragment);
    void inject(DataManager dataManager);
    void inject(CategoryDaoImpl categoryDao);
    void inject(ResultSearchActivity resultSearchActivity);
    void inject(DetailActivity detailActivity);
}
