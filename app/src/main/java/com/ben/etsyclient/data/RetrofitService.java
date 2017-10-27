package com.ben.etsyclient.data;

import com.ben.etsyclient.data.model.category.Categories;
import com.ben.etsyclient.data.model.goods.GoodsList;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface RetrofitService {

    @GET("v2/taxonomy/categories")
    Observable<Categories> getCategories(@Query("api_key") String apiKey);

    @GET("v2/listings/active")
    Observable<GoodsList> getItems(@Query("api_key") String apiKey, @Query("category") String category, @Query("keywords") String keywords, @Query("includes") String includes);

    @GET("v2/listings/active")
    Observable<GoodsList> getNextItems(@Query("api_key") String apiKey, @Query("category") String category,
                                       @Query("keywords") String keywords, @Query("includes") String includes,
                                       @Query("limit") int limit, @Query("offset") int offset);
}
