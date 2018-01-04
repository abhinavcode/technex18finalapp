package com.iitbhu.technex18.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

import com.iitbhu.technex18.R;
import com.iitbhu.technex18.adapters.GalleryAdapter;
import com.iitbhu.technex18.adapters.RecyclerItemClickListener;

import java.util.ArrayList;

public class Gallery extends AppCompatActivity {

    RecyclerView galleryRecycler;
    StaggeredGridLayoutManager gridLayoutManager;
    RecyclerView.Adapter adapter;
    int width,height;
    boolean overlay=false;
    View overview;

    ArrayList<Integer> res = new ArrayList<>();
    ArrayList<Bitmap> thumbs = new ArrayList<>();
    ArrayList<String> name_list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        width = metrics.widthPixels;
        height = metrics.heightPixels;

        name_list.add("Axelerate");
        name_list.add("R.C. Car");
        name_list.add("Extreme Engineering");
        name_list.add("Celebrating Innovation");
        name_list.add("Harley Davidson Show");
        name_list.add("Helicopter");
        name_list.add("Air Show");
        name_list.add("Intelligent Robots");
        name_list.add("Think Talks");


        res.add(R.drawable.car1);
        res.add(R.drawable.car2);
        res.add(R.drawable.car3);
        res.add(R.drawable.celeb_inno);
        res.add(R.drawable.harley);
        res.add(R.drawable.heli);
        res.add(R.drawable.plane2);
        res.add(R.drawable.robo);
        res.add(R.drawable.talk);

        for(int i=0;i<res.size();i++) {
            thumbs.add(getThumb(i));
        }

        galleryRecycler = (RecyclerView)findViewById(R.id.recycler_imagegallery);
        gridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        galleryRecycler.setLayoutManager(gridLayoutManager);
        adapter = new GalleryAdapter(getApplicationContext(),name_list,thumbs);
        galleryRecycler.setAdapter(adapter);
        galleryRecycler.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Log.d("Clicked", "" + position);
                        Intent zoom = new Intent(Gallery.this,FullscreenImageView.class);
                        zoom.putExtra("Resources",res);
                        zoom.putExtra("Titles",name_list);
                        zoom.putExtra("Position",position);
                        startActivity(zoom);
                    }
                })
        );
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    public Bitmap getThumb(int index) {
        Bitmap imageData = null;

        try {

            final int THUMBNAIL_WIDTH = width / 2;
            Bitmap imageBitmap = BitmapFactory.decodeResource(getResources(), res.get(index));

            int imgHeight = imageBitmap.getHeight();
            int imgWidth = imageBitmap.getWidth();
            int THUMBNAIL_HEIGHT = (imgHeight * THUMBNAIL_WIDTH) / imgWidth;

            imageBitmap = Bitmap.createScaledBitmap(imageBitmap, THUMBNAIL_WIDTH, THUMBNAIL_HEIGHT, false);
            imageData = imageBitmap;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return imageData;
    }
}
