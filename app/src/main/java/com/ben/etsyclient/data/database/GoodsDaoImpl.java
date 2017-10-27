package com.ben.etsyclient.data.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ben.etsyclient.EtsyClientApplication;
import com.ben.etsyclient.data.DBHelper;
import com.ben.etsyclient.data.model.MessageEvent;
import com.ben.etsyclient.data.model.goods.Goods;
import com.ben.etsyclient.data.model.goods.GoodsList;
import com.ben.etsyclient.data.model.goods.MainImage;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import javax.inject.Inject;

import static com.ben.etsyclient.util.EventConstants.EVENT_REFRESH;

public class GoodsDaoImpl implements GoodsDAO {

    private SQLiteDatabase sqLiteDatabase;

    @Inject
    DBHelper dbHelper;

    @Inject
    public GoodsDaoImpl() {
        EtsyClientApplication.getInjector().inject(this);
    }

    @Override
    public void open() {
        if (sqLiteDatabase == null) {
            try {
                sqLiteDatabase = dbHelper.getWritableDatabase();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public long saveGoods(Goods goods) {

        open();

        ContentValues contentGoods = new ContentValues();
        ContentValues contentImage = new ContentValues();

        MainImage image = goods.getMainImage();

        contentGoods.put(LISTING_ID, goods.getListingId());
        contentGoods.put(TITLE, goods.getTitle());
        contentGoods.put(DESCRIPTION, goods.getDescription());
        contentGoods.put(PRICE, goods.getPrice());
        contentGoods.put(CURRENCY_CODE, goods.getCurrencyCode());

        contentImage.put(LISTING_IMAGE_ID, image.getListingImageId());
        contentImage.put(LISTING_ID, image.getListingId());
        contentImage.put(URL_75x75, image.getUrl_75x75());
        contentImage.put(URL_170x135, image.getUrl_170x135());
        contentImage.put(URL_570xN, image.getUrl_570xN());
        contentImage.put(URL_FULLxFULL, image.getUrl_fullxfull());

        long insertGoods = sqLiteDatabase.insert(TABLE_GOODS, null, contentGoods);
        long insertImg =  sqLiteDatabase.insert(TABLE_MAIN_IMAGE, null, contentImage);

        if (insertGoods >= 0 && insertImg >= 0) {
            EventBus.getDefault().post(new MessageEvent(EVENT_REFRESH));
            return 1;
        }else {
            return -1;
        }
    }

    @Override
    public long deleteGoods(long id) {
        open();

        long insertGoods = sqLiteDatabase.delete(TABLE_GOODS, LISTING_ID + " = ?", new String[] {String.valueOf(id)});
        long insertImg =  sqLiteDatabase.delete(TABLE_MAIN_IMAGE, LISTING_ID + " = ?", new String[] {String.valueOf(id)});

        if (insertGoods >= 0 && insertImg >= 0) {
            EventBus.getDefault().post(new MessageEvent(EVENT_REFRESH));
            return 1;
        }else {
            return -1;
        }
    }

    @Override
    public GoodsList getGoodsList() {

        open();

        String query = TABLE_GOODS + " as G inner join " + TABLE_MAIN_IMAGE + " as I on G." + LISTING_ID + " = I." + LISTING_ID;

        GoodsList goodsList = new GoodsList();

        ArrayList<Goods> list = new ArrayList<>();

        Cursor cursorGoods = sqLiteDatabase.query(query, null, null, null, null, null, null);

        cursorGoods.moveToFirst();

        while (!cursorGoods.isAfterLast()) {

            Goods goods = cursorToGoods(cursorGoods);

            list.add(goods);

            cursorGoods.moveToNext();
        }

        goodsList.setResults(list);

        cursorGoods.close();

        return goodsList;
    }

    private Goods cursorToGoods(Cursor cursor) {

        Goods goods = new Goods();
        MainImage mainImage = new MainImage();

        goods.setListingId(cursor.getLong(cursor.getColumnIndex(LISTING_ID)));
        goods.setTitle(cursor.getString(cursor.getColumnIndex(TITLE)));
        goods.setDescription(cursor.getString(cursor.getColumnIndex(DESCRIPTION)));
        goods.setPrice(cursor.getDouble(cursor.getColumnIndex(PRICE)));
        goods.setCurrencyCode(cursor.getString(cursor.getColumnIndex(CURRENCY_CODE)));

        mainImage.setListingImageId(cursor.getLong(cursor.getColumnIndex(LISTING_IMAGE_ID)));
        mainImage.setListingId(cursor.getLong(cursor.getColumnIndex(LISTING_ID)));
        mainImage.setUrl_75x75(cursor.getString(cursor.getColumnIndex(URL_75x75)));
        mainImage.setUrl_170x135(cursor.getString(cursor.getColumnIndex(URL_170x135)));
        mainImage.setUrl_570xN(cursor.getString(cursor.getColumnIndex(URL_570xN)));
        mainImage.setUrl_fullxfull(cursor.getString(cursor.getColumnIndex(URL_FULLxFULL)));

        goods.setMainImage(mainImage);

        return goods;
    }
}
