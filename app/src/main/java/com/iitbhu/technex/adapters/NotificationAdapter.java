package com.iitbhu.technex18.adapters;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.iitbhu.technex18.R;
import com.iitbhu.technex18.utils.BoldModTextView;
import com.iitbhu.technex18.utils.ModTextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/**
 * Created by Abhinav on 02/01/2018.
 */
public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {
    ArrayList<String> mInitial = new ArrayList<>();
    ArrayList<String> mTitle = new ArrayList<>();
    ArrayList<String> mBody = new ArrayList<>();
    ArrayList<String> mDate = new ArrayList<>();

    private int [] colors={
            R.color.md_blue_500,                //Robonex
            R.color.md_red_500,             //Ascension
            R.color.md_green_500,             //Extreme
            R.color.md_pink_500,              //Supernova
            R.color.md_deep_purple_200,               //Modex
            R.color.md_deep_orange_600,               //Riqueza
            R.color.md_teal_500,             //Byte
            R.color.md_lime_600,                //Creatrix
    };

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public View mView;
        ModTextView initialtext, bodytext, datetext;
        BoldModTextView titletext;
        ImageView logoimage;
        CardView cardView;

        public ViewHolder(View v){
            super(v);
            initialtext=(ModTextView)v.findViewById(R.id.initialtext);
            titletext=(BoldModTextView) v.findViewById(R.id.titletext);
            datetext=(ModTextView)v.findViewById(R.id.datetext);
            bodytext=(ModTextView)v.findViewById(R.id.bodyttext);
            cardView=(CardView) v.findViewById(R.id.card_view);
            mView=v;
        }
    }

    public NotificationAdapter(
            ArrayList<String> initial,
            ArrayList<String> title,
            ArrayList<String> body,
            ArrayList<String> date){
        mInitial=initial;
        mTitle=title;
        mBody=body;
        mDate=date;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_news, parent, false);
        ViewHolder vh=new ViewHolder(v);
        return vh;
    }

    @Override
    public int getItemCount() {
        return mTitle.size();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd");
        Date resultdate = new Date(Long.parseLong(mDate.get(position)));
        holder.cardView.setCardBackgroundColor(colors[position%colors.length]);
        Log.d("Position", position%colors.length+"");
        holder.bodytext.setText(mBody.get(position));
        holder.datetext.setText(sdf.format(resultdate));
        holder.initialtext.setText(mInitial.get(position));
        holder.titletext.setText(mTitle.get(position));
    }
}
