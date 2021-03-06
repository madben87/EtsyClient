package com.ben.etsyclient.data.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ben.etsyclient.EtsyClientApplication;
import com.ben.etsyclient.data.DBHelper;
import com.ben.etsyclient.data.model.category.Categories;
import com.ben.etsyclient.data.model.category.Category;
import com.ben.etsyclient.util.MadLog;

import java.util.ArrayList;

import javax.inject.Inject;

public class CategoryDaoImpl implements CategoryDAO {

    private SQLiteDatabase sqLiteDatabase;

    @Inject
    DBHelper dbHelper;

    @Inject
    public CategoryDaoImpl() {
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
    public long addCategory(Category category) {

        open();

        ContentValues contentValues = new ContentValues();

        contentValues.put(CATEGORY_ID, category.getCategoryId());
        contentValues.put(NAME, category.getName());
        contentValues.put(META_TITLE, category.getMetaTitle());
        contentValues.put(META_KEYWORDS, category.getMetaKeywords());
        contentValues.put(META_DESCRIPTION, category.getMetaDescription());
        contentValues.put(PAGE_DESCRIPTION, category.getPageDescription());
        contentValues.put(PAGE_TITLE, category.getPageTitle());
        contentValues.put(CATEGORY_NAME, category.getCategoryName());
        contentValues.put(SHORT_NAME, category.getShortName());
        contentValues.put(LONG_NAME, category.getLongName());
        contentValues.put(NUM_CHILDREN, category.getNumChildren());

        return sqLiteDatabase.insert(TABLE_CATEGORY, null, contentValues);
    }

    @Override
    public void cachedCategory(Categories categories) {

        open();

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_CATEGORY);
        sqLiteDatabase.execSQL(DB_CATEGORY_CREATE);

        for (Category elem : categories.getResults()) {
            addCategory(elem);
            MadLog.log(this, "addCategory");
        }

        MadLog.log(this, "cachedCategory");
    }

    @Override
    public Categories getCategories() {

        open();

        ArrayList<Category> list = new ArrayList<>();

        Cursor cursor = sqLiteDatabase.query(TABLE_CATEGORY, null, null, null, null, null, null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Category category = cursorToCategory(cursor);
            list.add(category);
            cursor.moveToNext();
        }

        cursor.close();

        Categories categories = new Categories();
        categories.setCount(list.size());
        categories.setResults(list);

        MadLog.log(this, "getCategories");

        return categories;
    }

    private Category cursorToCategory(Cursor cursor) {

        Category category = new Category();

        category.setCategoryId(cursor.getLong(cursor.getColumnIndex(CATEGORY_ID)));
        category.setName(cursor.getString(cursor.getColumnIndex(NAME)));
        category.setMetaTitle(cursor.getString(cursor.getColumnIndex(META_TITLE)));
        category.setMetaKeywords(cursor.getString(cursor.getColumnIndex(META_KEYWORDS)));
        category.setMetaDescription(cursor.getString(cursor.getColumnIndex(META_DESCRIPTION)));
        category.setPageDescription(cursor.getString(cursor.getColumnIndex(PAGE_DESCRIPTION)));
        category.setPageTitle(cursor.getString(cursor.getColumnIndex(PAGE_TITLE)));
        category.setCategoryName(cursor.getString(cursor.getColumnIndex(CATEGORY_NAME)));
        category.setShortName(cursor.getString(cursor.getColumnIndex(SHORT_NAME)));
        category.setLongName(cursor.getString(cursor.getColumnIndex(LONG_NAME)));
        category.setNumChildren(cursor.getInt(cursor.getColumnIndex(NUM_CHILDREN)));

        return category;
    }
}
