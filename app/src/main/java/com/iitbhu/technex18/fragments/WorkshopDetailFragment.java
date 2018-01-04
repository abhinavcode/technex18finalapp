package com.iitbhu.technex18.fragments;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;

import com.iitbhu.technex18.R;
import com.iitbhu.technex18.database.DbMethods;
import com.iitbhu.technex18.utils.ModTextView;

import java.util.ArrayList;

import static com.iitbhu.technex18.database.DbConstants.COL_GUEST_LECTURE_DESIGNATION;
import static com.iitbhu.technex18.database.DbConstants.COL_GUEST_LECTURE_IMAGE;
import static com.iitbhu.technex18.database.DbConstants.COL_GUEST_LECTURE_LECTURER_BIO;
import static com.iitbhu.technex18.database.DbConstants.COL_GUEST_LECTURE_LECTURER_TYPE;
import static com.iitbhu.technex18.database.DbConstants.COL_GUEST_LECTURE_NAME;
import static com.iitbhu.technex18.database.DbConstants.COL_WORKSHOP_DESCRIPTION;
import static com.iitbhu.technex18.database.DbConstants.COL_WORKSHOP_FEES;

/**
 * Created by florentchampigny on 24/04/15.
 */
public class WorkshopDetailFragment extends Fragment {

    private ObservableScrollView mScrollView;
    private static final String ARG_SECTION_NUMBER = "section_number";
    int position;

    MaterialViewPager mViewPager;
    ViewPager viewPager;

    ModTextView tv_desc, tv_fees;

    DbMethods dbMethods;

    public static WorkshopDetailFragment newInstance(int sectionNumber) {
        WorkshopDetailFragment fragment = new WorkshopDetailFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_workshopdetail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mScrollView = (ObservableScrollView) view.findViewById(R.id.scrollView);
		MaterialViewPagerHelper.registerScrollView(getActivity(), mScrollView, null);

        dbMethods = new DbMethods(getActivity());

        position = getArguments().getInt(ARG_SECTION_NUMBER);

        Cursor cursor = dbMethods.queryWorkshop(null, null, null, null, null, null);

        mViewPager = (MaterialViewPager)getActivity().findViewById(R.id.materialViewPager);
        viewPager=mViewPager.getViewPager();

        ArrayList<String> descList = new ArrayList<String>();
        ArrayList<String> feesList = new ArrayList<String>();

        while (cursor.moveToNext()) {
            descList.add(cursor.getString(cursor.getColumnIndex(COL_WORKSHOP_DESCRIPTION)));
            feesList.add(cursor.getString(cursor.getColumnIndex(COL_WORKSHOP_FEES)));
        }

        final String[] descs = descList.toArray(new String[descList.size()]);
        final String[] fees = feesList.toArray(new String[feesList.size()]);

        String desc = descs[position-1];
        String fee = fees[position-1];

        tv_desc = (ModTextView)view.findViewById(R.id.tv_workshop_desc);
        tv_fees = (ModTextView)view.findViewById(R.id.tv_workshop_fees);

        tv_desc.setText(Html.fromHtml(desc).toString().trim());
        tv_fees.setText(fee);
    }
}
