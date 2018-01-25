package com.iitbhu.technex18.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;

import com.iitbhu.technex18.R;
import com.iitbhu.technex18.adapters.NotificationAdapter;
import com.iitbhu.technex18.database.DbConstants;
import com.iitbhu.technex18.database.DbMethods;

import java.util.ArrayList;

/**
 * Created by Abhinav on 02/01/2018.
 */
public class NewsFragment extends Fragment implements DbConstants{
    public NewsFragment() {
    }

    RecyclerView personallist;
    LinearLayoutManager personallayoutmanager;
    RecyclerView.Adapter personaladapter;

    DbMethods dbMethods;

    ArrayList<String> mInitial = new ArrayList<>();
    ArrayList<String> mTitle = new ArrayList<>();
    ArrayList<String> mBody = new ArrayList<>();
    ArrayList<String> mDate = new ArrayList<>();

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static NewsFragment newInstance() {
        NewsFragment fragment = new NewsFragment();
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_news, container, false);

        /*final float scale = getContext().getResources().getDisplayMetrics().density;
        int pixels = (int) (56 * scale + 0.5f);
        AppBarLayout appBarLayout = (AppBarLayout)getActivity().findViewById(R.id.app_bar);
        appBarLayout.getLayoutParams().height = pixels;
        Toolbar toolbar = (Toolbar)getActivity().findViewById(R.id.toolbar);
        toolbar.getLayoutParams().height = 0;*/
        Toolbar toolbar = (Toolbar)getActivity().findViewById(R.id.toolbar);

//        mInitial.add("A");
//        mTitle.add("Aladeen Madafaka");
//        mDate.add("14th Dec");
//        mBody.add("Admiral General Aladeen, the liberator of Wadia and protector of goats and malawakh!");
//
//        mInitial.add("B");
//        mTitle.add("Benedict Cumberbatch");
//        mDate.add("31st Oct");
//        mBody.add("Good for nothing British twat who is actually one hellishly amazing actor!");
//
//        mInitial.add("A");
//        mTitle.add("Aladeen Madafaka");
//        mDate.add("14th Dec");
//        mBody.add("Admiral General Aladeen, the liberator of Wadia and protector of goats and malawakh!");
//
//        mInitial.add("B");
//        mTitle.add("Benedict Cumberbatch");
//        mDate.add("31st Oct");
//        mBody.add("Good for nothing British twat who is actually one hellishly amazing actor!");

        dbMethods = new DbMethods(getActivity());
        Cursor cursor = dbMethods.queryUpdates(null, null, null, null, null, COL_UPDATES_TIME + " DESC");

        while (cursor.moveToNext()) {
            mTitle.add(cursor.getString(cursor.getColumnIndex(COL_UPDATES_TITLE)));
            mBody.add(cursor.getString(cursor.getColumnIndex(COL_UPDATES_TEXT)));
            mInitial.add(cursor.getString(cursor.getColumnIndex(COL_UPDATES_TITLE)).substring(0,1));
            mDate.add(cursor.getString(cursor.getColumnIndex(COL_UPDATES_TIME)));
        }


        personallist = (RecyclerView)rootView.findViewById(R.id.notificationlist);
        personallayoutmanager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        personallist.setLayoutManager(personallayoutmanager);
        personallist.addItemDecoration(new MaterialViewPagerHeaderDecorator());
        personaladapter = new NotificationAdapter(mInitial, mTitle, mBody, mDate);
        personallist.setAdapter(personaladapter);

        return rootView;
    }
}
