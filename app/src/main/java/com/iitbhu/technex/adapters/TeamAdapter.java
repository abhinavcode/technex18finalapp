package com.iitbhu.technex18.adapters;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.iitbhu.technex18.R;
import com.iitbhu.technex18.utils.BoldModTextView;
import com.iitbhu.technex18.utils.ModTextView;
import com.iitbhu.technex18.utils.RoundedImageView;
import com.squareup.picasso.Picasso;

/**
 * Created by abhinav on 13/2/18.
 */

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.ViewHolder> {
    private String[] mName, mNumber, mDesg, mImages,mEmail;
    Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public View mView;
        BoldModTextView name_text;
        ModTextView desg_text;
        RoundedImageView logo_img;
        ImageView call,mail;
        public ViewHolder(View v){
            super(v);
            name_text=(BoldModTextView)v.findViewById(R.id.name);
            desg_text=(ModTextView)v.findViewById(R.id.post);
            call= (ImageView) v.findViewById(R.id.call);
            mail=(ImageView)v.findViewById(R.id.mail);
            logo_img=(RoundedImageView) v.findViewById(R.id.pic);
            mView=v;
        }
    }

    public TeamAdapter(Context context, String[]name_text, String[] number_text, String[] desg_text, String[] email_text, String[] images){
        mName=name_text;
        mDesg=desg_text;
        mNumber=number_text;
        mImages=images;
        mEmail=email_text;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_team, parent, false);
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

        holder.name_text.setText(mName[position]);
        holder.desg_text.setText(mDesg[position]);
        if(mNumber[position]==null){
            holder.call.setVisibility(View.GONE);
        }
        holder.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mNumber[position]!=null) {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:" +mNumber[position]));
                    view.getContext().startActivity(intent);
                }
            }
        });

        holder.mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mNumber[position]!=null) {
                    Intent email = new Intent(Intent.ACTION_SEND);
                    email.putExtra(Intent.EXTRA_EMAIL,new String[] { mEmail[position]});
                    email.putExtra(Intent.EXTRA_SUBJECT,"Technex'18");
                    email.putExtra(Intent.EXTRA_TEXT,"sent a message using the contact us on Technex app");

                    email.setType("message/rfc822");
                    view.getContext().startActivity(email);
                }
            }
        });
        if(!mImages[position].equals(""))
        Picasso.with(context)
                .load(mImages[position])
                .placeholder(R.drawable.owl)
                .error(R.drawable.owl)
                .into(holder.logo_img);
    }
}
