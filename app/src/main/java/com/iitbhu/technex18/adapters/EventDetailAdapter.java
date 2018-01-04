package com.iitbhu.technex18.adapters;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.iitbhu.technex18.R;
import com.iitbhu.technex18.utils.BoldModTextView;
import com.iitbhu.technex18.utils.ModTextView;

import java.util.Random;


/**
 * Created by root on 1/10/16.
 */




public class EventDetailAdapter extends RecyclerView.Adapter<EventDetailAdapter.ViewHolder> {
    private String[] mName, mSubs;

    Random random = new Random();
    private int mPos;
    private Bitmap[] mBitmaps,mLogo,mBg;
    Context context;

    private int[] bgs = {
            R.drawable.bgmatblue,
            R.drawable.bgmatpink,
            R.drawable.bgmatgreen,
            R.drawable.bgmatyellow,
    };

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public View mView;
        BoldModTextView name_text;
        ModTextView sub_text;
        ImageView bg_img;
        ImageView logo_img;
        LinearLayout layout;

        public ViewHolder(View v){
            super(v);
            name_text=(BoldModTextView)v.findViewById(R.id.title);
            sub_text=(ModTextView)v.findViewById(R.id.body);
            layout=(LinearLayout)v.findViewById(R.id.cardLayout);
            bg_img=(ImageView)v.findViewById(R.id.bg);
            //logo_img=(ImageView)v.findViewById(R.id.iv_workshop_icon);*/
            mView=v;
        }
    }

    public EventDetailAdapter(Context context, String[] name_text, String[] body_text, int pos){
        mName=name_text;
        mSubs=body_text;
        mPos=pos;
        this.context = context;
        // mBg=bg_image;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_eventdetail_textplace, parent, false);
        ViewHolder vh=new ViewHolder(v);
        return vh;
    }

    @Override
    public int getItemCount() {
        return mName.length;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        Log.d("Position",position+"");

        Log.d("Pos",mPos+"");
        holder.bg_img.setImageResource(bgs[mPos%bgs.length]);

        if (mName[position].toUpperCase().substring(0,4).equals("PROB") && mSubs[position].substring(0,4).equals("http")) {

            holder.name_text.setText(mName[position]);
            holder.sub_text.setText("Click here to download the problem statement");

            holder.sub_text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(mSubs[position]));
                    context.startActivity(intent);
                }
            });
        } else {

            holder.name_text.setText(mName[position]);
            holder.sub_text.setText(mSubs[position]);
        }
        /*holder.bg_img.setImageBitmap(mBg[position]);
        holder.logo_img.setImageBitmap(mLogo[position]);*/
    }
}
