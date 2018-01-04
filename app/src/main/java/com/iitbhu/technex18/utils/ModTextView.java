package com.iitbhu.technex18.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Soumyadeep on 03-Dec-15.
 */
public class ModTextView extends TextView {
    public ModTextView(Context context, AttributeSet attrs, int defStyle){
        super(context,attrs,defStyle);
        init();
    }

    public ModTextView(Context context, AttributeSet attrs){
        super(context,attrs);
        init();
    }

    public ModTextView(Context context){
        super(context);
        init();
    }
    private void init(){
        Typeface tf=Typeface.createFromAsset(getContext().getAssets(),"OpenSans-Regular.ttf");
        setTypeface(tf);
    }
}
