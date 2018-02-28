package com.iitbhu.technex18.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;

import com.iitbhu.technex18.R;
import com.iitbhu.technex18.activities.EventDetail;
import com.iitbhu.technex18.adapters.EventListAdapter;
import com.iitbhu.technex18.adapters.RecyclerItemClickListener;
import com.iitbhu.technex18.database.DbConstants;
import com.iitbhu.technex18.database.DbMethods;

import java.util.ArrayList;

/**
 * Created by Abhinav on 02/01/2018.
 */
public class EventListFragment extends Fragment implements DbConstants {

    RecyclerView.Adapter mEventCategoryAdapter;
    RecyclerView mEventRecycler;
    RecyclerView.LayoutManager mEventLayoutManager;
    static int countclick=0;

    private ProgressDialog progress;


    private static final String ARG_SECTION_NUMBER = "section_number";
    DbMethods dbMethods;

    public EventListFragment() {
    }

    @Override
    public void onResume() {
        super.onResume();
        if (progress.isShowing()) {
            progress.dismiss();
        }
    }

    public static EventListFragment newInstance(int sectionNumber) {
        EventListFragment fragment = new EventListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_event, container, false);


        progress = new ProgressDialog(getActivity());
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setMessage("Loading. Please wait...");
        progress.setCancelable(false);
        progress.setIndeterminate(true);


        dbMethods = new DbMethods(getActivity());

        String[] parentCategories = {"Robonex", "Ascension", "Extreme Engineering", "Supernova","Modex", "Riqueza", "Byte The Bits",  "Pahal"};



        int pageNo = getArguments().getInt(ARG_SECTION_NUMBER);
        Log.d("pageNo", pageNo + "");
        Cursor cursor=dbMethods.queryEventsRaw(parentCategories[pageNo]);

//        Cursor cursor = dbMethods.queryEvents(null, COL_EVENTS_PARENT_CATEGORY+" = ?", new String[]{parentCategories[pageNo]+""}, null, null, COL_EVENTS_EVENT_ORDER);

        ArrayList<String> nameList = new ArrayList<String>();
        ArrayList<String> descList = new ArrayList<String>();
        ArrayList<Integer> memberList = new ArrayList<>();
        ArrayList<Integer> prizeList = new ArrayList<>();
        ArrayList<Integer> IDList = new ArrayList<>();
        int vals=0;
        while (cursor.moveToNext()) {
            Log.d("events in "+parentCategories[pageNo],vals+"");
            vals++;
            nameList.add(cursor.getString(cursor.getColumnIndex(COL_EVENTS_NAME)));
            descList.add(cursor.getString(cursor.getColumnIndex(COL_EVENTS_DESCRIPTION)));
            memberList.add(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COL_EVENTS_TEAM_SIZE))));
            prizeList.add(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COL_EVENTS_PRIZE_MONEY))));
            IDList.add(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COL_EVENTS_ID))));
        }
        final String[] names = nameList.toArray(new String[nameList.size()]);
        final String[] desc = descList.toArray(new String[descList.size()]);
        final  Integer[] team = memberList.toArray(new Integer[memberList.size()]);
        final  Integer[] prize = prizeList.toArray(new Integer[prizeList.size()]);
        final  Integer[] IDs = IDList.toArray(new Integer[IDList.size()]);

        final int images = R.drawable.bggradient;

        mEventRecycler = (RecyclerView) rootView.findViewById(R.id.my_recycler_view_1);
        mEventLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mEventRecycler.setLayoutManager(mEventLayoutManager);
        mEventCategoryAdapter = new EventListAdapter(getActivity(),names, desc, team, prize, getArguments().getInt(ARG_SECTION_NUMBER), images);
        mEventRecycler.setAdapter(mEventCategoryAdapter);
        mEventRecycler.addItemDecoration(new MaterialViewPagerHeaderDecorator());
        mEventRecycler.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Log.d("Clicked", "" + position);
                        progress.show();
                        Intent intent = new Intent(getActivity(),EventDetail.class);
                        intent.putExtra("EVENTNAME",names[position]);
                        intent.putExtra("POSITION",position);
                        intent.putExtra("ID",IDs[position]+"");
                        startActivity(intent);
                    }
                })
        );
        return rootView;
    }
}