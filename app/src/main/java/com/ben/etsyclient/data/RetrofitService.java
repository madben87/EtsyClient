package com.ben.etsyclient.data;

import com.ben.etsyclient.model.category.Categories;
import com.ben.etsyclient.model.goods.GoodsList;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface RetrofitService {

    //https://openapi.etsy.com/v2/listings/active?api_key=kg64vu4dsbfzm5o6fgl27vf8&category=paper_goods&keywords=terminator&includes=MainImage

    //https://openapi.etsy.com/v2/listings/active?api_key=kg64vu4dsbfzm5o6fgl27vf8&category=paper_goods&keywords=terminator&includes=MainImage&limit=25&offset=0 - pagination

    @GET("v2/taxonomy/categories")
    Observable<Categories> getCategories(@Query("api_key") String apiKey);

    @GET("v2/listings/active")
    Observable<GoodsList> getItems(@Query("api_key") String apiKey, @Query("category") String category, @Query("keywords") String keywords, @Query("includes") String includes);

    @GET("v2/listings/active")
    Observable<GoodsList> getNextItems(@Query("api_key") String apiKey, @Query("category") String category,
                                       @Query("keywords") String keywords, @Query("includes") String includes,
                                       @Query("limit") int limit, @Query("offset") int offset);

    /*@GET("v2/listings/active?api_key={api_key}&category={category}&keywords={keywords}&includes=MainImage")
    Observable<GoodsList> getItems(@Path("api_key") String apiKey, @Path("category") String category, @Path("keywords") String keywords);*/
}
