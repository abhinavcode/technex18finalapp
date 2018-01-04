package com.iitbhu.technex18.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;

import com.iitbhu.technex18.R;
import com.iitbhu.technex18.adapters.EventDetailAdapter;
import com.iitbhu.technex18.adapters.RecyclerItemClickListener;

/**
 * Created by root on 15/12/16.
 */
public class EventDetailFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String ARG_TITLE = "title";
    private static final String ARG_DESC = "desc";

    static String mName, mContent;
    static private int mPos;

    public EventDetailFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static EventDetailFragment newInstance(int pos, String name, String content) {
        EventDetailFragment fragment = new EventDetailFragment();
        Bundle args = new Bundle();
        mPos = pos;
        args.putString(ARG_TITLE, name);
        args.putString(ARG_DESC, content);
        args.putInt(ARG_SECTION_NUMBER, pos);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_event_intro, container, false);

        RecyclerView mEventRecycler;
        RecyclerView.Adapter mEventAdapter;
        RecyclerView.LayoutManager mEventLayoutManager;

        mName = getArguments().getString(ARG_TITLE);
        mContent = getArguments().getString(ARG_DESC);



        String[] title = {mName};
//        String[] body = {"A material metaphor is the unifying theory of a rationalized space and a system of motion." +
//                "The material is grounded in tactile reality, inspired by the study of paper and ink, yet " +
//                "technologically advanced and open to imagination and magic.\n" +
//                "Surfaces and edges of the material provide visual cues that are grounded in reality. The " +
//                "use of familiar tactile attributes helps users quickly understand affordances. Yet the " +
//                "flexibility of the material creates new affordances that supercede those in the physical " +
//                "world, without breaking the rules of physics.\n" +
//                "The fundamentals of light, surface, and movement are key to conveying how objects move, " +
//                "interact, and exist in space and in relation to each other. Realistic lighting shows " +
//                "seams, divides space, and indicates moving parts.\n\n"+
//                "A material metaphor is the unifying theory of a rationalized space and a system of motion." +
//                "The material is grounded in tactile reality, inspired by the study of paper and ink, yet " +
//                "technologically advanced and open to imagination and magic.\n" +
//                "Surfaces and edges of the material provide visual cues that are grounded in reality. The " +
//                "use of familiar tactile attributes helps users quickly understand affordances. Yet the " +
//                "flexibility of the material creates new affordances that supercede those in the physical " +
//                "world, without breaking the rules of physics.\n" +
//                "The fundamentals of light, surface, and movement are key to conveying how objects move, " +
//                "interact, and exist in space and in relation to each other. Realistic lighting shows " +
//                "seams, divides space, and indicates moving parts.\n\n" +
//                "A material metaphor is the unifying theory of a rationalized space and a system of motion." +
//                "The material is grounded in tactile reality, inspired by the study of paper and ink, yet " +
//                "technologically advanced and open to imagination and magic.\n" +
//                "Surfaces and edges of the material provide visual cues that are grounded in reality. The " +
//                "use of familiar tactile attributes helps users quickly understand affordances. Yet the " +
//                "flexibility of the material creates new affordances that supercede those in the physical " +
//                "world, without breaking the rules of physics.\n" +
//                "The fundamentals of light, surface, and movement are key to conveying how objects move, " +
//                "interact, and exist in space and in relation to each other. Realistic lighting shows " +
//                "seams, divides space, and indicates moving parts.\n\n" +
//                "Bold, graphic, intentional.\n\n" +
//                "The foundational elements of print based design typography, grids, space, scale, color, " +
//                "and use of imagery guide visual treatments. These elements do far more than please the " +
//                "eye. They create hierarchy, meaning, and focus. Deliberate color choices, edge to edge " +
//                "imagery, large scale typography, and intentional white space create a bold and graphic " +
//                "interface that immerse the user in the experience.\n" +
//                "An emphasis on user actions makes core functionality immediately apparent and provides " +
//                "waypoints for the user.\n\n" +
//                "Motion provides meaning.\n\n",
//
//                   /* "Motion respects and reinforces the user as the prime mover. Primary user actions are " +
//                            "inflection points that initiate motion, transforming the whole design.\n" +
//                            "All action takes place in a single environment. Objects are presented to the user without " +
//                            "breaking the continuity of experience even as they transform and reorganize.\n" +
//                            "Motion is meaningful and appropriate, serving to focus attention and maintain continuity. " +
//                            "Feedback is subtle yet clear. Transitions are efﬁcient yet coherent.\n\n" +
//                            "3D world.\n\n" +
//                            "The material environment is a 3D space, which means all objects have x, y, and z " +
//                            "dimensions. The z-axis is perpendicularly aligned to the plane of the display, with the " +
//                            "positive z-axis extending towards the viewer. Every sheet of material occupies a single " +
//                            "position along the z-axis and has a standard 1dp thickness.\n" +
//                            "On the web, the z-axis is used for layering and not for perspective. The 3D world is " +
//                            "emulated by manipulating the y-axis.\n\n" +
//                            "Light and shadow.\n\n" +
//                            "Within the material environment, virtual lights illuminate the scene. Key lights create " +
//                            "directional shadows, while ambient light creates soft shadows from all angles.\n" +
//                            "Shadows in the material environment are cast by these two light sources. In Android " +
//                            "development, shadows occur when light sources are blocked by sheets of material at " +
//                            "various positions along the z-axis. On the web, shadows are depicted by manipulating the " +
//                            "y-axis only. The following example shows the card with a height of 6dp.\n\n" +
//                            "Resting elevation.\n\n" +
//                            "All material objects, regardless of size, have a resting elevation, or default elevation " +
//                            "that does not change. If an object changes elevation, it should return to its resting " +
//                            "elevation as soon as possible.\n\n" +
//                            "Component elevations.\n\n" +
//                            "The resting elevation for a component type is consistent across apps (e.g., FAB elevation " +
//                            "does not vary from 6dp in one app to 16dp in another app).\n" +
//                            "Components may have different resting elevations across platforms, depending on the depth " +
//                            "of the environment (e.g., TV has a greater depth than mobile or desktop).\n\n" +
//                            "Responsive elevation and dynamic elevation offsets.\n\n",
//
//                    "Some component types have responsive elevation, meaning they change elevation in response " +
//                            "to user input (e.g., normal, focused, and pressed) or system events_mat. These elevation " +
//                            "changes are consistently implemented using dynamic elevation offsets.\n" +
//                            "Dynamic elevation offsets are the goal elevation that a component moves towards, relative " +
//                            "to the component’s resting state. They ensure that elevation changes are consistent " +
//                            "across actions and component types. For example, all components that lift on press have " +
//                            "the same elevation change relative to their resting elevation.\n" +
//                            "Once the input event is completed or cancelled, the component will return to its resting " +
//                            "elevation.\n\n" +
//                            "Avoiding elevation interference.\n\n"*/
//        };
//
//            /*Bitmap background[] = {
//                    BitmapFactory.decodeResource(getResources(), R.drawable.sae),
//                    BitmapFactory.decodeResource(getResources(), R.drawable.aero),
//                    BitmapFactory.decodeResource(getResources(), R.drawable.robo),
//                    BitmapFactory.decodeResource(getResources(), R.drawable.sae)
//            };*/
//
//            /*Bitmap icon1 = BitmapFactory.decodeResource(getResources(), R.drawable.cart_outline);
//            Bitmap icons[] = {icon1, icon1, icon1, icon1};*/

        String[] body = {Html.fromHtml(mContent).toString()};

        mEventRecycler = (RecyclerView) rootView.findViewById(R.id.my_recycler_view_1);
        mEventLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mEventRecycler.setLayoutManager(mEventLayoutManager);
        mEventRecycler.addItemDecoration(new MaterialViewPagerHeaderDecorator());
        Log.d("Position from Frag",mPos+"");
        mEventAdapter = new EventDetailAdapter(getActivity(),title,body,mPos);
        mEventRecycler.setAdapter(mEventAdapter);
        mEventRecycler.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Log.d("Clicked", "" + position);
                            /*Intent intent = new Intent(getActivity(),EventListActivity.class);
                            intent.putExtra("POSITION",position);
                            startActivity(intent);*/
                    }
                })
        );

            /*LinearLayout frag_layout = (LinearLayout)rootView.findViewById(R.id.layout_detail_frag);
            BoldModTextView title = (BoldModTextView)rootView.findViewById(R.id.title);

            int[] colors = {getResources().getColor(R.color.md_red_trans),
                    getResources().getColor(R.color.md_green_trans),
                    getResources().getColor(R.color.md_deep_orange_trans),
                    getResources().getColor(R.color.md_teal_trans),
                    getResources().getColor(R.color.md_pink_trans),
            };

            String[] titles={"Introduction", "Event Structure", "Problem Statement", "Register","Contact Us"};

            frag_layout.setBackgroundColor(colors[getArguments().getInt(ARG_SECTION_NUMBER)]);

            title.setText(titles[getArguments().getInt(ARG_SECTION_NUMBER)]);*/
        return rootView;
    }
}
