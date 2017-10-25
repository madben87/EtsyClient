package com.ben.etsyclient.util;

public interface DBConstants extends Constants {

    String DB_NAME = "etsy.db";
    int DB_VERSION = 1;
    String TABLE_CATEGORY = "category";
    String TABLE_GOODS = "goods";
    String TABLE_MAIN_IMAGE = "mainImage";

    String DB_CATEGORY_CREATE = "create table "
            + TABLE_CATEGORY + " ("
            + CATEGORY_ID + " integer primary key autoincrement, "
            + NAME + " text not null, "
            + META_TITLE + " text not null, "
            + META_KEYWORDS + " text not null, "
            + META_DESCRIPTION + " text not null, "
            + PAGE_DESCRIPTION + " text not null, "
            + PAGE_TITLE + " text not null, "
            + CATEGORY_NAME + " text not null, "
            + SHORT_NAME + " text not null, "
            + LONG_NAME + " text not null, "
            + NUM_CHILDREN + " integer"
            + ") ;"
            ;

    String DB_GOODS_CREATE = "create table "
            + TABLE_GOODS + " ("
            + LISTING_ID + " integer primary key autoincrement, "
            + TITLE + " text not null, "
            + DESCRIPTION + " text, "
            + PRICE + " real, "
            + CURRENCY_CODE + " text"
            + ") ;"
            ;

    String DB_MAIN_IMAGE_CREATE = "create table "
            + TABLE_MAIN_IMAGE + " ("
            + LISTING_IMAGE_ID + " integer primary key autoincrement, "
            + LISTING_ID + " integer not null, "
            + URL_75x75 + " text, "
            + URL_170x135 + " text, "
            + URL_570xN + " text, "
            + URL_FULLxFULL + " text, "
            + "FOREIGN KEY(" + LISTING_ID + ") REFERENCES " + TABLE_GOODS + "(" + LISTING_ID + ")"
            + ") ;"
            ;
}
