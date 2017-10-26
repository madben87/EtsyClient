package com.ben.etsyclient.view.saved_view;

import com.ben.etsyclient.Presenter;
import com.ben.etsyclient.model.goods.Goods;

public interface SavedPresenter extends Presenter<SavedView> {

    //Вызывает метод Repository.getItems для загрузки данных из БД и метод SaveView.showItems для отображения
    void getItems();
    //Вызывает метод Repository.deleteItem для удаления элемента из БД
    void deleteItem(long id);

    void showDialog(Goods goods);
}
