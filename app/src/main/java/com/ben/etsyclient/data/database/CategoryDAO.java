package com.ben.etsyclient.data.database;

import com.ben.etsyclient.data.model.category.Categories;
import com.ben.etsyclient.data.model.category.Category;

public interface CategoryDAO extends DAO {

    long addCategory(Category category);

    void cachedCategory(Categories categories);

    Categories getCategories();
}
