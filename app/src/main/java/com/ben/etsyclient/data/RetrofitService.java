package com.ben.etsyclient.data;

import com.ben.etsyclient.model.category.Categories;

import retrofit2.http.GET;
import rx.Observable;

import static com.ben.etsyclient.util.Constants.APP_KEY;

public interface RetrofitService {

    String categoryPath = "v2/taxonomy/categories?api_key=" + APP_KEY;

    @GET(categoryPath)
    Observable<Categories> getCategories();
}
