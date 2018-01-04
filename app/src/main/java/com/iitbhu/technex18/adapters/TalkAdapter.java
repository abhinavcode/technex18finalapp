package com.iitbhu.technex18.adapters;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import com.iitbhu.technex18.R;
import com.iitbhu.technex18.utils.BoldModTextView;
import com.iitbhu.technex18.utils.ModTextView;
import com.iitbhu.technex18.utils.RoundedImageView;


public class TalkAdapter extends RecyclerView.Adapter<TalkAdapter.ViewHolder> {
    private String[] mName, mSubs, mDesc, mImages;
    private int[] mBg;
    Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public View mView;
        BoldModTextView name_text;
        ModTextView subs_text, desc_text;
        ImageView bg_img;
        RoundedImageView logo_img;
        public ViewHolder(View v){
            super(v);
            name_text=(BoldModTextView)v.findViewById(R.id.talk_name);
            subs_text=(ModTextView)v.findViewById(R.id.talk_subs);
            desc_text=(ModTextView) v.findViewById(R.id.talk_desc);
            bg_img=(ImageView)v.findViewById(R.id.talk_bg);
            logo_img=(RoundedImageView) v.findViewById(R.id.talk_icon);
            mView=v;
        }
    }

    public TalkAdapter(Context context, String[]name_text, String[] subs_text, String[] desc_text, int[] bg_image, String[] images){
        mName=name_text;
        mSubs=subs_text;
        mDesc=desc_text;
        mImages=images;
        mBg=bg_image;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_talks, parent, false);
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
        holder.subs_text.setText(mSubs[position]);
        holder.desc_text.setText(mDesc[position]);
        holder.bg_img.setImageResource(mBg[position]);
        Picasso.with(context)
                .load(mImages[position])
                .placeholder(R.drawable.owl)
                .error(R.drawable.owl)
                .into(holder.logo_img);
    }
}
