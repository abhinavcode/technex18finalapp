package com.iitbhu.technex18.adapters;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.iitbhu.technex18.utils.BoldModTextView;

import com.iitbhu.technex18.R;
import com.iitbhu.technex18.utils.ModTextView;

/**
 * Created by Soumyadeep on 02-Dec-15.
 */
public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.ViewHolder> {
    private String[] mName, mSubs, me1, me2,me3;
    private int[] mRating;
    private Bitmap[] mLogo,mBg;
    int i=1;
    private int [] grads={
            R.drawable.gradientred,                //Robonex
            R.drawable.gradientpurple,             //Ascension
            R.drawable.gradientorange,             //Extreme
            R.drawable.gradientblueb,              //Supernova
            R.drawable.gradientpink,               //Modex
            R.drawable.gradientteal,               //Riqueza
            R.drawable.gradientorange,             //Byte
            R.drawable.gradientred,                //Creatrix
            R.drawable.gradientgreen               //Pahal
    };

    private int [] logos={
            R.drawable.ic_robonex,                //Robonex
            R.drawable.ic_ascension,             //Ascension
            R.drawable.ic_extreme,             //Extreme
            R.drawable.ic_supernova,              //Supernova
            R.drawable.ic_modex,               //Modex
            R.drawable.ic_riqueza,               //Riqueza
            R.drawable.ic_byte,             //Byte
            R.drawable.ic_creatrix,                //Creatrix
            R.drawable.ic_pahal               //Pahal
    };

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public View mView;
        BoldModTextView name_text;
        ModTextView sub_text,event1,event2,event3;
        ImageView bg_img;
        LinearLayout gradientLayout;
        ImageView logo_img;
        public ViewHolder(View v){
            super(v);
            name_text=(BoldModTextView)v.findViewById(R.id.title_events);
            bg_img=(ImageView)v.findViewById(R.id.banner_events);
            logo_img=(ImageView)v.findViewById(R.id.icon_events);
            sub_text=(ModTextView)v.findViewById(R.id.subtitle_events);
            event1=(ModTextView)v.findViewById(R.id.e1);
            event2=(ModTextView)v.findViewById(R.id.e2);
            event3=(ModTextView)v.findViewById(R.id.e3);
            gradientLayout=(LinearLayout)v.findViewById(R.id.gradientlayout);
            mView=v;
        }
    }

    public EventsAdapter(String[] name_text, String[] sub_text, Bitmap[] bg_image, Bitmap[] logo_img, String[] e1,String[] e2,String[] e3){
        mName=name_text;
        mSubs=sub_text;
        mLogo=logo_img;
        mBg=bg_image;
        me1=e1;
        me2=e2;
        me3=e3;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_events, parent, false);
        ViewHolder vh=new ViewHolder(v);
        Log.d("i",i+"");
        i++;
        return vh;
    }

    @Override
    public int getItemCount() {
        return mName.length;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.name_text.setText(mName[position]);
        holder.sub_text.setText(mSubs[position]);
        holder.bg_img.setImageBitmap(mBg[position]);
        holder.logo_img.setImageResource(logos[position]);
        holder.event1.setText(me1[position]);
        holder.event2.setText(me2[position]);
        holder.event3.setText(me3[position]);
        holder.gradientLayout.setBackgroundResource(grads[(position)%grads.length]);
    }
}
