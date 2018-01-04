package com.iitbhu.technex18.adapters;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.iitbhu.technex18.R;
import com.iitbhu.technex18.utils.BoldModTextView;
import com.iitbhu.technex18.utils.ModTextView;

/**
 * Created by Soumyadeep on 02-Dec-15.
 */
public class NotifCarouselAdapter extends RecyclerView.Adapter<NotifCarouselAdapter.ViewHolder> {
    private String[] mTitle, mSubtitle;
    private Bitmap[] mImg;



    public static class ViewHolder extends RecyclerView.ViewHolder{
        public View mView;
        BoldModTextView title_text;
        ModTextView subtitle_text;
        ImageView notif_img, prev, next;
        public ViewHolder(View v){
            super(v);
            title_text=(BoldModTextView)v.findViewById(R.id.title_text);
            subtitle_text=(ModTextView)v.findViewById(R.id.subtitle_text);
            notif_img=(ImageView)v.findViewById(R.id.notif_img);
            next=(ImageView)v.findViewById(R.id.arrow_next);
            prev=(ImageView)v.findViewById(R.id.arrow_prev);
            mView=v;
        }
    }

    public NotifCarouselAdapter(String[] title, String[] subtitle, Bitmap[] img){
        mTitle=title;
        mSubtitle=subtitle;
        mImg=img;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_notif_carousel, parent, false);
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

        holder.title_text.setText(mTitle[position]);
        holder.subtitle_text.setText(mSubtitle[position]);
        holder.notif_img.setImageBitmap(mImg[position]);

        if(position==0){
            holder.prev.setVisibility(View.GONE);
        }
        else if(position==mTitle.length-1){
            holder.next.setVisibility(View.GONE);
        }
        else{
            holder.prev.setVisibility(View.VISIBLE);
            holder.next.setVisibility(View.VISIBLE);
        }
    }
}
