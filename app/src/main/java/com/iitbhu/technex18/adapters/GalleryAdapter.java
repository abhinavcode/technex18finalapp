package com.iitbhu.technex18.adapters;

import android.annotation.TargetApi;
import android.content.Context;
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

import java.util.ArrayList;

/**
 * Created by Soumyadeep on 02-Dec-15.
 */
public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {
    ArrayList<Bitmap> mBg = new ArrayList<>();
    ArrayList<String> mName = new ArrayList<>();
    Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public View mView;
        BoldModTextView name_text;
        ModTextView event1,event2,event3;
        ImageView bg_img;
        ImageView logo_img;
        public ViewHolder(View v){
            super(v);
            name_text=(BoldModTextView)v.findViewById(R.id.tv_image);
            bg_img=(ImageView)v.findViewById(R.id.iv_image);
            mView=v;
        }
    }

    public GalleryAdapter(Context context, ArrayList<String> name_text, ArrayList<Bitmap> bg_image){
        mName=name_text;
        mBg=bg_image;
        this.context=context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_gallery, parent, false);
        ViewHolder vh=new ViewHolder(v);
        return vh;
    }

    @Override
    public int getItemCount() {
        return mName.size();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.name_text.setText(mName.get(position));
        holder.bg_img.setImageBitmap(mBg.get(position));

/*        Picasso.with(context)
                .load(mBg.get(po))
                .placeholder(R.drawable.user_placeholder)
                .error(R.drawable.user_placeholder_error)
                .into(holder.bg_img);*/
    }
}
