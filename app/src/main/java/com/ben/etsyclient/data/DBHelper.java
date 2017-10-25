package com.ben.etsyclient.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ben.etsyclient.util.DBConstants;
import com.ben.etsyclient.util.MadLog;

import javax.inject.Inject;

public class DBHelper extends SQLiteOpenHelper implements DBConstants {

    @Inject
    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB_CATEGORY_CREATE);
        db.execSQL(DB_GOODS_CREATE);
        db.execSQL(DB_MAIN_IMAGE_CREATE);
        MadLog.log(this, "onCreate");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_CATEGORY);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_GOODS);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_MAIN_IMAGE);
        onCreate(db);
        MadLog.log(this, "onUpgrade");
    }
}
