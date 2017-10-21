package com.ben.etsyclient.view.search_view;

import com.ben.etsyclient.MVPView;
import com.ben.etsyclient.model.category.Categories;

public interface SearchView extends MVPView {

    void showCategories(Categories categories);
}
