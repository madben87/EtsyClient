package com.ben.etsyclient.view.detail_view;

import com.ben.etsyclient.Presenter;
import com.ben.etsyclient.model.goods.Goods;

public interface DetailPresenter extends Presenter<DetailView> {

    //При нажатии на кнопку "сохранить" вызывает метод Repository.saveGoods для сохранения элемента в БД
    void saveGoods(Goods goods);
    //Вызывает метод Repository.deleteItem для удаления элемента из БД
    void deleteItem(long id);
    //Этот метод вызывается при старте активити и вызывает метод DetailActivity.showDetail(Goods goods) для отображения товара
    void startView(Goods goods);
}
