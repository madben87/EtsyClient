package com.ben.etsyclient.util;

import android.content.Context;
import android.graphics.Typeface;

import com.ben.etsyclient.EtsyClientApplication;

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

        EtsyClientApplication.getInjector().inject(this);

        setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/jazz_ball.ttf"));
    }
}
