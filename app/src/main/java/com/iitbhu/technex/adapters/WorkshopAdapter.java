package com.iitbhu.technex18.adapters;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

import com.iitbhu.technex18.R;
import com.iitbhu.technex18.utils.BoldModTextView;


/**
 * Created by Abhinav on 02/01/2018.
 */


public class WorkshopAdapter extends RecyclerView.Adapter<WorkshopAdapter.ViewHolder> {
    private String[] mName;
    private int[] mColor;
    private String images[];
    Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public View mView;
        BoldModTextView name_text;
        ImageView logo_img;
        LinearLayout linearLayout;
        public ViewHolder(View v){
            super(v);
            name_text=(BoldModTextView)v.findViewById(R.id.tv_workshoptitle);
            logo_img=(ImageView)v.findViewById(R.id.iv_workshop_icon);
            linearLayout=(LinearLayout)v.findViewById(R.id.navig_frame);
            mView=v;
        }
    }

    public WorkshopAdapter(Context context, String[] name_text, Bitmap[] logo_img, int[] colorlist, String[] imageList){
        mName=name_text;
        mColor=colorlist;
        images=imageList;
        this.context=context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_workshoplist, parent, false);
        ViewHolder vh=new ViewHolder(v);
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
        holder.linearLayout.setBackgroundResource(mColor[position%mColor.length]);
        Picasso.with(context)
                .load(Uri.parse(images[position]))
                .placeholder(R.drawable.ic_workshop)
                .error(R.drawable.ic_workshop)
                .into(holder.logo_img);
    }
}
