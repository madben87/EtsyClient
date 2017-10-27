package com.ben.etsyclient.arch.modules;

import com.ben.etsyclient.data.RetrofitService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RetrofitModule {

    @Provides
    @Singleton
    public RetrofitService providesDataService() {
        return new Retrofit.Builder()
                .baseUrl("https://openapi.etsy.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build().create(RetrofitService.class);
    }
}
