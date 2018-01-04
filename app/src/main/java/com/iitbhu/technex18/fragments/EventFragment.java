package com.iitbhu.technex18.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;

import com.iitbhu.technex18.R;
import com.iitbhu.technex18.activities.EventListActivity;
import com.iitbhu.technex18.adapters.EventsAdapter;
import com.iitbhu.technex18.adapters.RecyclerItemClickListener;
import com.iitbhu.technex18.utils.Constants;

/**
 * Created by root on 18/9/16.
 */
public class EventFragment extends Fragment implements Constants {
    public EventFragment() {
    }

    SharedPreferences myprefs;

    private ProgressDialog progress;


    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static EventFragment newInstance() {
        EventFragment fragment = new EventFragment();
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (progress.isShowing()) {
            progress.dismiss();
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_event, container, false);

        progress = new ProgressDialog(getActivity());
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setMessage("Loading events. Please wait...");
        progress.setCancelable(false);
        progress.setIndeterminate(true);

        /*final float scale = getContext().getResources().getDisplayMetrics().density;
        int pixels = (int) (56 * scale + 0.5f);
        AppBarLayout appBarLayout = (AppBarLayout)getActivity().findViewById(R.id.app_bar);
        appBarLayout.getLayoutParams().height = pixels;
        Toolbar toolbar = (Toolbar)getActivity().findViewById(R.id.toolbar);
        toolbar.getLayoutParams().height = 0;*/

        RecyclerView mEventRecycler;
        RecyclerView.Adapter mEventAdapter;
        RecyclerView.LayoutManager mEventLayoutManager;


        String[] names = {"Robonex", "Ascension", "Extreme Engineering","Supernova", "Modex", "Riqueza", "Byte The Bits", "Creatrix",  "Pahal"};
        String[] subs = {"An adrenaline charged event where bots clash to outwit and outmanoeuvre in a battle of wills and skills. Robonex brings to you a plethora of opportunities to highlight your knowledge of robotics and the mathematics and the algorithm that underpin it.",
                "To most people sky is the limit. To those who love aviation, the sky is home. So if you're one of those flying enthusiasts who believes in the ideology of: eat, sleep, fly, repeat;  we've got the perfect platform to showcase your aero modelling skills. Come join us at the aero modelling extravaganza of the year and get ready to take your flight to success!",
                "The world brings to you those who are willing to get their hands dirty. And here , we want you to do exactly that. Let go of the mad scientist inside you, and bring your engineering dream to life. Make your very own race car, or design bridges, or contraption, or hydraulic motion , this is where the real test of engineering happens, in the field.",
                "With supernova its time to go beyond the horizons and explore the interstellar in the quest to unvield the universe's mysterious truth. We bring to you quizzes and star gazing events that judge your knowledge about star studded campus abve all of us.",
                "Modex is the event for those who dare to change the world. This event exhibits innovative solutions to intricate social or industrial problems. Modex is the event for the next big thing! Modex is a distinctive model exhibitions event with innovative genres. Moodex is home to many ground breaking ideas and designs as participants come up with unique ways to provide intricate solutions to highly complex problems.",
                "With an eye on the cut-throat competitions in the corporate world, we give you a chance to polish and shine your managerial skills in this, one of a kind amalgamation of numerous business application focussed events. From the stock market to that crazy big idea, we have an event for everything that your inner businessman wants.",
                "It is a competition that will test your coding skills to the limit with the mix of events judging analytics and mathematical acumen. Byte the bits ensures enticing prize money, exciting problems and a chance to compete with the best.",
                "Creatrix demonstrates ingenuity at its best. It's time to turn your imaginations into reality with competitions involving animation, design, documentary and photography. So let your mind flow free and come up with a masterpiece.",
                "Responsibility is a big word. As the future of this nation, and its only hope, this is our attempt to give back to the society in our own unique way. One of the signature events of Technex, Pahal intends to nurture a new breed of socially conscious technocrats with the skills and temperament to make our society and this world a better place to live in, by providing innovative engineering solutions to its problems and dilemmas."};

        String[] e1 = {"Robowars", "Drone-Tech", "Axelerate", "Exploring the Interstellar", "Open Software", "\ud835\udec62 : Economist's Enigma", "Capture The Flag", "2D", "Aagaz"};
        String[] e2 = {"Maze-Xplorer", "D'Aero-glisseur", "Goldberg's Alley","AstroPhotography", "Open Hardware", "Krackat", "Appathon", "Animaze",  "Sampann"};
        String[] e3 = {"Hurdlemania", "La-trajectoire", "Bridge-It","Scientists of Utopia", "Green Tech", "Manthan", "International Coding Marathon", "Avant Garde",  "Swachch"};
        Bitmap background[] = {
                BitmapFactory.decodeResource(getResources(), R.drawable.bannerrobonex),
                BitmapFactory.decodeResource(getResources(), R.drawable.bannerascension),
                BitmapFactory.decodeResource(getResources(), R.drawable.bannerextreme),
                BitmapFactory.decodeResource(getResources(), R.drawable.bannersupernova),
                BitmapFactory.decodeResource(getResources(), R.drawable.bannermodex),
                BitmapFactory.decodeResource(getResources(), R.drawable.bannerriqueza),
                BitmapFactory.decodeResource(getResources(), R.drawable.bannerbyte),
                BitmapFactory.decodeResource(getResources(), R.drawable.bannercreatrix),
                BitmapFactory.decodeResource(getResources(), R.drawable.bannerpahal)
        };

        Bitmap icon1 = BitmapFactory.decodeResource(getResources(), R.drawable.robo_white);
        Bitmap icons[] = {icon1, icon1, icon1, icon1, icon1, icon1, icon1, icon1, icon1};

        myprefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

        mEventRecycler = (RecyclerView) rootView.findViewById(R.id.my_recycler_view_1);
        mEventLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mEventRecycler.setLayoutManager(mEventLayoutManager);
        mEventRecycler.addItemDecoration(new MaterialViewPagerHeaderDecorator());
        mEventAdapter = new EventsAdapter(names, subs,background, icons,e1,e2,e3);
        mEventRecycler.setAdapter(mEventAdapter);
        mEventRecycler.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Log.d("Clicked", "" + position);
                        progress.show();
                        Intent intent = new Intent(getActivity(), EventListActivity.class);
                        intent.putExtra("POSITION",position);
                        startActivity(intent);
                    }
                })
        );
        return rootView;
    }

}
