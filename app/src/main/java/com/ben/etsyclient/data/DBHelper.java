package com.ben.etsyclient.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.ben.etsyclient.util.DBConstants;
import com.ben.etsyclient.util.MadLog;

import javax.inject.Inject;

public class DBHelper extends SQLiteOpenHelper implements DBConstants {

    private static final String DB_CATEGORY_CREATE = "create table "
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

    @Inject
    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB_CATEGORY_CREATE);
        MadLog.log(this, "onCreate");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_CATEGORY);
        onCreate(db);
        MadLog.log(this, "onUpgrade");
    }
}
