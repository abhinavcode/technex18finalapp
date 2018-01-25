package com.iitbhu.technex18.adapters;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.iitbhu.technex18.R;
import com.iitbhu.technex18.utils.BoldModTextView;
import com.iitbhu.technex18.utils.ModTextView;

/**
 * Created by Abhinav on 02/01/2018.
 */
public class NavigHomeAdapter extends RecyclerView.Adapter<NavigHomeAdapter.ViewHolder> {
    private String[] mTitle, mSubtitle;
    private Bitmap[] mImg;
    private int[] mColors;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public View mView;
        BoldModTextView title_text;
        ModTextView subtitle_text;
        ImageView icon_img, prev, next;
        FrameLayout card_frame;
        public ViewHolder(View v){
            super(v);
            title_text=(BoldModTextView)v.findViewById(R.id.title_navig);
            subtitle_text=(ModTextView)v.findViewById(R.id.subtitle_navig);
            icon_img = (ImageView)v.findViewById(R.id.navig_img);
            card_frame = (FrameLayout)v.findViewById(R.id.navig_frame);
            mView=v;
        }
    }

    public NavigHomeAdapter(String[] title, String[] subtitle, Bitmap[] img, int[] color){
        mTitle=title;
        mSubtitle=subtitle;
        mImg=img;
        mColors = color;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_square_navig, parent, false);
        ViewHolder vh=new ViewHolder(v);
        return vh;
    }

    @Override
    public int getItemCount() {
        return mTitle.length;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if(position==0){

        }
        else{
            holder.title_text.setText(mTitle[position]);
            holder.subtitle_text.setText(mSubtitle[position]);
            holder.icon_img.setImageBitmap(mImg[position]);
            holder.card_frame.setBackgroundColor(mColors[position]);
        }

    }
}
