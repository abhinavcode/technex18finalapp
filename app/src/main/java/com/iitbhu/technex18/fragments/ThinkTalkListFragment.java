package com.iitbhu.technex18.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;

import com.iitbhu.technex18.R;
import com.iitbhu.technex18.adapters.RecyclerItemClickListener;
import com.iitbhu.technex18.adapters.TalkAdapter;
import com.iitbhu.technex18.database.DbMethods;

import java.util.ArrayList;

import static com.iitbhu.technex18.database.DbConstants.COL_GUEST_LECTURE_DESIGNATION;
import static com.iitbhu.technex18.database.DbConstants.COL_GUEST_LECTURE_IMAGE;
import static com.iitbhu.technex18.database.DbConstants.COL_GUEST_LECTURE_LECTURER_BIO;
import static com.iitbhu.technex18.database.DbConstants.COL_GUEST_LECTURE_NAME;

/**
 * Created by root on 18/9/16.
 */
public class ThinkTalkListFragment extends Fragment {
    public ThinkTalkListFragment() {
    }

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    DbMethods dbMethods;


    MaterialViewPager mViewPager;
    ViewPager viewPager;


    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static ThinkTalkListFragment newInstance() {
        ThinkTalkListFragment fragment = new ThinkTalkListFragment();
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_thinklist, container, false);

        dbMethods = new DbMethods(getActivity());

        Cursor cursor = dbMethods.queryGuestLectures(null, null, null, null, null, null);

        mViewPager = (MaterialViewPager)getActivity().findViewById(R.id.materialViewPager);
        viewPager=mViewPager.getViewPager();

        ArrayList<String> nameList = new ArrayList<String>();
        ArrayList<String> subsList = new ArrayList<String>();
        ArrayList<String> descList = new ArrayList<String>();
        ArrayList<String> imageList = new ArrayList<String>();

        while (cursor.moveToNext()) {
            nameList.add(cursor.getString(cursor.getColumnIndex(COL_GUEST_LECTURE_NAME)));
            subsList.add(cursor.getString(cursor.getColumnIndex(COL_GUEST_LECTURE_DESIGNATION)));
            descList.add(Html.fromHtml(cursor.getString(cursor.getColumnIndex(COL_GUEST_LECTURE_LECTURER_BIO))).toString());
            imageList.add(cursor.getString(cursor.getColumnIndex(COL_GUEST_LECTURE_IMAGE)));
        }

        final String[] names = nameList.toArray(new String[nameList.size()]);
        final String[] subs = subsList.toArray(new String[descList.size()]);
        final String[] desc = descList.toArray(new String[descList.size()]);
        final String[] images = imageList.toArray(new String[imageList.size()]);

        int bitmap1= R.drawable.matbag1;
        int bitmap2= R.drawable.matbag2;
        int bitmap3= R.drawable.matbag3;
        int bitmap4= R.drawable.matbag4;

        final int background[]={bitmap1,bitmap2,bitmap3,bitmap4, bitmap1, bitmap2, bitmap3,bitmap4};


        mRecyclerView = (RecyclerView)rootView.findViewById(R.id.thinktalklist);
        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());
        mAdapter = new TalkAdapter(getActivity(),names,subs,desc,background,images);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Log.d("Workshop Clicked", "" + position);
                        viewPager.setCurrentItem(position+1);

                    }
                })
        );

        return rootView;
    }
}
