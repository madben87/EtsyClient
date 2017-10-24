package com.ben.etsyclient;

import android.app.Application;
import android.content.Context;

import com.ben.etsyclient.modules.ContextModule;
import com.ben.etsyclient.util.MadLog;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

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

        MadLog.setDebugMode(true);

        injector = DaggerInjector
                .builder()
                .contextModule(new ContextModule(this))
                .build();

        initImageLoader(getApplicationContext());
    }

    public static void initImageLoader(Context context) {

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();

        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.defaultDisplayImageOptions (options);
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        config.writeDebugLogs(); // Remove for release app

        ImageLoader.getInstance().init(config.build());
    }

    public static Injector getInjector() {
        return getInstance().injector;
    }
}
