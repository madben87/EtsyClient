package com.ben.etsyclient.view.saved;

import android.app.DialogFragment;

import com.ben.etsyclient.arch.MVPView;
import com.ben.etsyclient.data.model.goods.GoodsList;

public interface SavedView extends MVPView {

    //При старте главного экрана фрагмент вызывает метод SavePresenter.getItems
    //Этот метод отображает данные во вью
    void showItems(GoodsList goodsList);
    //Вызывает метод для отображения сообщения в Toast
    void showMessage(String msg);

    void showDialog(DialogFragment dialog);
}
