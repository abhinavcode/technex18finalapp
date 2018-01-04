package com.iitbhu.technex18.fragments;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.iitbhu.technex18.R.id;
import com.iitbhu.technex18.R.layout;

public class FullscreenImageFragment extends Fragment {

    private static final String BUNDLE_ASSET = "asset";

    private int mRes;

    public FullscreenImageFragment() {
    }

    public void setAsset(int res) {
        this.mRes = res;
    }

    Bitmap bmp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(layout.view_pager_page, container, false);

        if (savedInstanceState != null) {
            mRes = savedInstanceState.getInt("res");
        }

        SubsamplingScaleImageView imageView = (SubsamplingScaleImageView)rootView.findViewById(id.imageView);
            imageView.setImage(ImageSource.resource(mRes));


        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        View rootView = getView();
        outState.putInt("res", mRes);

    }

}
