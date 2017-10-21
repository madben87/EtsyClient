package com.ben.etsyclient.util;

import android.content.Context;
import android.graphics.Typeface;

import com.ben.etsyclient.EtsyClient;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class JazzBallFont {

    private static Typeface typeface;

    @Inject
    public Context context;

    public static Typeface getTypeface() {
        return typeface;
    }

    private static void setTypeface(Typeface typeface) {
        JazzBallFont.typeface = typeface;
    }

    @Inject
    public JazzBallFont() {

        EtsyClient.getInjector().inject(this);

        setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/jazz_ball.ttf"));
    }
}
