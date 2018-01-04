package com.iitbhu.technex18.fragments;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.squareup.picasso.Picasso;

import com.iitbhu.technex18.R;
import com.iitbhu.technex18.database.DbMethods;
import com.iitbhu.technex18.utils.BoldModTextView;
import com.iitbhu.technex18.utils.ModTextView;
import com.iitbhu.technex18.utils.RoundedImageView;

import java.util.ArrayList;

import static com.iitbhu.technex18.database.DbConstants.COL_GUEST_LECTURE_DESIGNATION;
import static com.iitbhu.technex18.database.DbConstants.COL_GUEST_LECTURE_IMAGE;
import static com.iitbhu.technex18.database.DbConstants.COL_GUEST_LECTURE_LECTURER_BIO;
import static com.iitbhu.technex18.database.DbConstants.COL_GUEST_LECTURE_LECTURER_TYPE;
import static com.iitbhu.technex18.database.DbConstants.COL_GUEST_LECTURE_NAME;

/**
 * Created by florentchampigny on 24/04/15.
 */
public class ThinkTalkDetailFragment extends Fragment {

    private ObservableScrollView mScrollView;
    private static final String ARG_SECTION_NUMBER = "section_number";
    int position;

    BoldModTextView name_text;
    ModTextView desg_text, desc_text, date_text, topic_text;
    RoundedImageView pic_img;

    MaterialViewPager mViewPager;
    ViewPager viewPager;

    DbMethods dbMethods;


    public static ThinkTalkDetailFragment newInstance(int sectionNumber) {
        ThinkTalkDetailFragment fragment = new ThinkTalkDetailFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_thinkdetail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mScrollView = (ObservableScrollView) view.findViewById(R.id.scrollView);
		MaterialViewPagerHelper.registerScrollView(getActivity(), mScrollView, null);

        dbMethods = new DbMethods(getActivity());

        position = getArguments().getInt(ARG_SECTION_NUMBER);

        Cursor cursor = dbMethods.queryGuestLectures(null, null, null, null, null, null);

        mViewPager = (MaterialViewPager)getActivity().findViewById(R.id.materialViewPager);
        viewPager=mViewPager.getViewPager();

        ArrayList<String> nameList = new ArrayList<String>();
        ArrayList<String> subsList = new ArrayList<String>();
        ArrayList<String> descList = new ArrayList<String>();
        ArrayList<String> imageList = new ArrayList<String>();
        ArrayList<String> typeList = new ArrayList<String>();

        while (cursor.moveToNext()) {
            nameList.add(cursor.getString(cursor.getColumnIndex(COL_GUEST_LECTURE_NAME)));
            subsList.add(cursor.getString(cursor.getColumnIndex(COL_GUEST_LECTURE_DESIGNATION)));
            descList.add(Html.fromHtml(cursor.getString(cursor.getColumnIndex(COL_GUEST_LECTURE_LECTURER_BIO))).toString());
            imageList.add(cursor.getString(cursor.getColumnIndex(COL_GUEST_LECTURE_IMAGE)));
            typeList.add(cursor.getString(cursor.getColumnIndex(COL_GUEST_LECTURE_LECTURER_TYPE)));
        }

        final String[] names = nameList.toArray(new String[nameList.size()]);
        final String[] subs = subsList.toArray(new String[descList.size()]);
        final String[] desc = descList.toArray(new String[descList.size()]);
        final String[] images = imageList.toArray(new String[imageList.size()]);
        final String[] types = typeList.toArray(new String[typeList.size()]);

        String name = names[position-1];
        String desg = subs[position-1];
        String description = desc[position-1];
        String type = types[position-1];

        name_text=(BoldModTextView)view.findViewById(R.id.talk_name);
        desg_text=(ModTextView)view.findViewById(R.id.talk_subs);
        desc_text=(ModTextView)view.findViewById(R.id.talk_desc);
        date_text=(ModTextView)view.findViewById(R.id.talk_date);
        topic_text=(ModTextView)view.findViewById(R.id.talk_title);
        pic_img=(RoundedImageView)view.findViewById(R.id.talk_icon);

        Log.d("what the faaaa" + position, images[position-1]);

        name_text.setText(name);
        desg_text.setText(desg);
        desc_text.setText(description);
        date_text.setText(type);
        Picasso.with(getActivity())
                .load(images[position-1])
                .placeholder(R.drawable.owl)
                .error(R.drawable.owl)
                .into(pic_img);
    }
}
