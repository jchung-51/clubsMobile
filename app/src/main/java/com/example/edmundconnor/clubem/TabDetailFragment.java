package com.example.edmundconnor.clubem;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.edmundconnor.clubem.dummy.DummyContent;

import static com.example.edmundconnor.clubem.R.id.tabView;

/**
 * A fragment representing a single Tab detail screen.
 * This fragment is either contained in a {@link TabListActivity}
 * in two-pane mode (on tablets) or a {@link TabDetailActivity}
 * on handsets.
 */
public class TabDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private DummyContent.DummyItem mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public TabDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.content);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            RelativeLayout tabView = (RelativeLayout) rootView.findViewById(R.id.tabView);
            /**switch(mItem.toString()) {
                case "Feed":
                    Intent intent = new Intent(getActivity(), FeedActivity.class);
                    startActivity(intent);
                    return rootView;
                case "Calendar":
                    Intent intent2 = new Intent(getActivity(), CalendarActivity.class);
                    startActivity(intent2);
                    return rootView;
                case "myClubs":
                    Intent intent3 = new Intent(getActivity(), myClubs.class);
                    startActivity(intent3);
                    return rootView;
                case "allClubs":
                    Intent intent4 = new Intent(getActivity(), allClubs.class);
                    startActivity(intent4);
                    return rootView;
                case "Profile":
                    Intent intent5 = new Intent(getActivity(), ProfileActivity.class);
                    startActivity(intent5);
                    return rootView;
            }*/

        }

        return rootView;
    }
}
