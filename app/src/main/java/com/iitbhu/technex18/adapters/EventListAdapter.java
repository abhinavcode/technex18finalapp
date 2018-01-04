package com.iitbhu.technex18.adapters;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.iitbhu.technex18.R;
import com.iitbhu.technex18.utils.BoldModTextView;
import com.iitbhu.technex18.utils.ModTextView;

/**
 * Created by Soumyadeep on 02-Dec-15.
 */
public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.ViewHolder> {
    private String[] mName, mDesc; int posi=-1;
    private Integer[] mPrize, mTeam;
    private int images;
    private int [] grads={R.drawable.gradientred,R.drawable.gradientgreen,R.drawable.gradientpurple,R.drawable.gradientblueb,R.drawable.gradientorange,R.drawable.gradientteal};
    Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public View mView;
        BoldModTextView name_text;
        ModTextView desc_text, team_text, prize_text;
        LinearLayout l,lgrad, memberView;
        View sep;
        ImageView bg_img;
        ImageView ivEvents;
        public ViewHolder(View v){
            super(v);
            name_text=(BoldModTextView)v.findViewById(R.id.tv_events);
            desc_text=(ModTextView)v.findViewById(R.id.tv_desc);
            team_text=(ModTextView)v.findViewById(R.id.team_text);
            prize_text=(ModTextView)v.findViewById(R.id.prize_text);
            sep=(View)v.findViewById(R.id.viewSep);
            memberView=(LinearLayout)v.findViewById(R.id.memberView);
            lgrad=(LinearLayout)v.findViewById(R.id.gradientlayout);
            bg_img=(ImageView)v.findViewById(R.id.bg);

            mView=v;

            ivEvents=(ImageView)v.findViewById(R.id.iv_events_icon);
        }
    }

    public EventListAdapter(Context context, String[] name_text, String[] desc_text, Integer[] team_text, Integer[] prize_text, int pos, int imageList){
        mName=name_text;
        mDesc=desc_text;
        mTeam=team_text;
        mPrize=prize_text;
        posi=pos;
        images=imageList;
        this.context=context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_eventlist, parent, false);
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
        String desc = Html.fromHtml(mDesc[position]).toString();
        if(desc.equals("")){
            desc = "Click here for more details";
        }
        holder.desc_text.setText(desc);
        holder.lgrad.setBackgroundResource(grads[(position+posi)%grads.length]);
        if(mTeam[position]!=0) {
            holder.team_text.setText("Members : " + mTeam[position] + " Max");
        } else{
            holder.sep.setVisibility(View.GONE);
            holder.memberView.setVisibility(View.GONE);
        }

        holder.prize_text.setText("Prize : "+mPrize[position]);

        switch (posi) {
            case 0:
                holder.ivEvents.setImageResource(R.drawable.ic_robonex);
                break;
            case 1:
                holder.ivEvents.setImageResource(R.drawable.ic_ascension);
                break;
            case 2:
                holder.ivEvents.setImageResource(R.drawable.ic_extreme);
                break;
            case 3:
                holder.ivEvents.setImageResource(R.drawable.ic_supernova);
                break;
            case 4:
                holder.ivEvents.setImageResource(R.drawable.ic_modex);
                break;
            case 5:
                holder.ivEvents.setImageResource(R.drawable.ic_riqueza);
                break;
            case 6:
                holder.ivEvents.setImageResource(R.drawable.ic_byte);
                break;
            case 7:
                holder.ivEvents.setImageResource(R.drawable.ic_creatrix);
                break;
            case 8:
                holder.ivEvents.setImageResource(R.drawable.ic_pahal);
                break;
            default:
                holder.ivEvents.setImageResource(R.drawable.robo_white);
        }
        /*Picasso.with(context)
                .load(images)
                .placeholder(R.drawable.bggradient)
                .error(R.drawable.bggradient)
                .into(holder.bg_img);*/

        //    holder.bg_img.setImageBitmap(mBg[position]);
    }
}