package com.ben.etsyclient.view.saved_view;

import android.app.DialogFragment;

import com.ben.etsyclient.MVPView;
import com.ben.etsyclient.model.goods.GoodsList;

public interface SavedView extends MVPView {

    //При старте главного экрана фрагмент вызывает метод SavePresenter.getItems
    //Этот метод отображает данные во вью
    void showItems(GoodsList goodsList);
    //Вызывает метод для отображения сообщения в Toast
    void showMessage(String msg);

    void showDialog(DialogFragment dialog);
}
