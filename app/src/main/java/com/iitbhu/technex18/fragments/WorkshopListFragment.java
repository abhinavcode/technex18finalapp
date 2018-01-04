package com.iitbhu.technex18.fragments;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;

import com.iitbhu.technex18.R;
import com.iitbhu.technex18.adapters.RecyclerItemClickListener;
import com.iitbhu.technex18.adapters.WorkshopAdapter;
import com.iitbhu.technex18.database.DbMethods;

import java.util.ArrayList;

import static com.iitbhu.technex18.database.DbConstants.COL_WORKSHOP_IMAGE;
import static com.iitbhu.technex18.database.DbConstants.COL_WORKSHOP_TITLE;

/**
 * Created by root on 18/9/16.
 */
public class WorkshopListFragment extends Fragment {
    public WorkshopListFragment() {
    }

    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    MaterialViewPager mViewPager;
    ViewPager viewPager;

    DbMethods dbMethods;

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static WorkshopListFragment newInstance() {
        WorkshopListFragment fragment = new WorkshopListFragment();
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_workshoplist, container, false);

        dbMethods = new DbMethods(getActivity());

        Cursor cursor = dbMethods.queryWorkshop(null, null, null, null, null, null);

        mViewPager = (MaterialViewPager)getActivity().findViewById(R.id.materialViewPager);
        viewPager=mViewPager.getViewPager();

        ArrayList<String> nameList = new ArrayList<String>();
        ArrayList<String> imageList = new ArrayList<String>();

        while (cursor.moveToNext()) {
            nameList.add(cursor.getString(cursor.getColumnIndex(COL_WORKSHOP_TITLE)));
            imageList.add(cursor.getString(cursor.getColumnIndex(COL_WORKSHOP_IMAGE)));
        }

        final String[] names = nameList.toArray(new String[nameList.size()]);
        final String[] images = imageList.toArray(new String[imageList.size()]);

        Bitmap icon1= BitmapFactory.decodeResource(getResources(), R.drawable.ic_workshop);
        Bitmap icons[]={icon1,icon1,icon1,icon1,icon1,icon1,icon1,icon1};

        mViewPager = (MaterialViewPager)getActivity().findViewById(R.id.materialViewPager);
        viewPager=mViewPager.getViewPager();

        int colorlist[] = new int[]{
                R.drawable.gradientred,
                R.drawable.gradientgreen,
                R.drawable.gradientblue,
                R.drawable.gradientorange,
                R.drawable.gradientpurple,
                R.drawable.gradientteal,
                R.drawable.gradientblueb,
                R.drawable.gradientpink
        };


        mRecyclerView = (RecyclerView)rootView.findViewById(R.id.notificationlist);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mRecyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());
        mAdapter = new WorkshopAdapter(getActivity(),names,icons,colorlist,images);
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
