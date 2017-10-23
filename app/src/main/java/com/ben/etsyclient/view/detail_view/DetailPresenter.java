package com.ben.etsyclient.view.detail_view;

import com.ben.etsyclient.Presenter;
import com.ben.etsyclient.model.goods.Goods;

public interface DetailPresenter extends Presenter<DetailView> {

    //При нажатии на кнопку "сохранить" вызывает метод Repository.saveItem для сохранения элемента в БД
    void saveItem(Goods goods);

    //Этот метод вызывается при старте активити и вызывает метод DetailActivity.showDetail(Goods goods) для отображения товара
    void startView(Goods goods);
}
