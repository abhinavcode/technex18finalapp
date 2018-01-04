package com.iitbhu.technex18.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by shriyansh on 16/1/16.
 */
public class BoldModTextView extends TextView {
    Context context;
    public BoldModTextView(Context context) {
        super(context);
        init();
    }

    public BoldModTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BoldModTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        Typeface tf=Typeface.createFromAsset(getContext().getAssets(),"OpenSans-Regular.ttf");
        setTypeface(tf,Typeface.BOLD);
    }
}
