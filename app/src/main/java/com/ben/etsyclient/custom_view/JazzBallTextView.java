package com.ben.etsyclient.custom_view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

import com.ben.etsyclient.EtsyClient;
import com.ben.etsyclient.util.JazzBallFont;

import javax.inject.Inject;

@SuppressLint("AppCompatCustomView")
public class JazzBallTextView extends TextView {

    @Inject
    JazzBallFont jazzBallFont;

    public JazzBallTextView(Context context) {
        super(context);
        setType();
    }

    public JazzBallTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setType();
    }

    public JazzBallTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void setType() {
        EtsyClient.getInjector().inject(this);
        super.setTypeface(jazzBallFont.getTypeface());
    }
}
