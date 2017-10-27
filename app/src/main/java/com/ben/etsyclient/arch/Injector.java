package com.ben.etsyclient.arch;

import com.ben.etsyclient.view.custom.JazzBallTextView;
import com.ben.etsyclient.data.DataManager;
import com.ben.etsyclient.data.database.CategoryDaoImpl;
import com.ben.etsyclient.data.database.GoodsDaoImpl;
import com.ben.etsyclient.arch.modules.ContextModule;
import com.ben.etsyclient.arch.modules.RetrofitModule;
import com.ben.etsyclient.util.JazzBallFont;
import com.ben.etsyclient.view.detail.DetailActivity;
import com.ben.etsyclient.view.saved.DeleteItemDialog;
import com.ben.etsyclient.view.saved.SavedFragment;
import com.ben.etsyclient.view.result.ResultSearchActivity;
import com.ben.etsyclient.view.result.ResultSearchPresenterImpl;
import com.ben.etsyclient.view.search.SearchFragment;

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
    void inject(ResultSearchPresenterImpl resultSearchPresenter);
    void inject(DetailActivity detailActivity);
    void inject(GoodsDaoImpl goodsDao);
    void inject(SavedFragment savedFragment);
    void inject(DeleteItemDialog deleteItemDialog);
}
