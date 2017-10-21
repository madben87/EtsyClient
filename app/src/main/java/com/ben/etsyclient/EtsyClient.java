package com.ben.etsyclient;

import android.app.Application;

import com.ben.etsyclient.modules.ContextModule;

public class EtsyClient extends Application {

    private Injector injector;

    private static EtsyClient etsyClientInstance;

    public static EtsyClient getInstance() {
        return etsyClientInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        etsyClientInstance = this;

        injector = DaggerInjector
                .builder()
                .contextModule(new ContextModule(this))
                .build();
    }

    public static Injector getInjector() {
        return getInstance().injector;
    }
}
