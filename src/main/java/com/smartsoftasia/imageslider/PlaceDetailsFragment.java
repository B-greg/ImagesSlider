package com.smartsoftasia.imageslider;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.smartsoftasia.imageslider.viewPagerIndicator.CirclePageIndicator;
import com.smartsoftasia.imageslider.viewPagerIndicator.PageIndicator;

/**
 * Created by gregoire barret on 2/12/15.
 * For SmartCommerce project.
 */
public class PlaceDetailsFragment extends Fragment {

    PlaceSlidesFragmentAdapter mAdapter;
    ViewPager mPager;
    PageIndicator mIndicator;
    Object[] images;

    public static final String TAG = "detailsFragment";
    private static final String ARG_IMAGES_URL = "image_url";
    
    
    public static PlaceDetailsFragment newInstance(String[] strings){
        PlaceDetailsFragment fragment = new PlaceDetailsFragment();
        Bundle args = new Bundle();
        args.putStringArray(ARG_IMAGES_URL, strings);
        fragment.setArguments(args);
        return fragment;
        
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            images = getArguments().getStringArray(ARG_IMAGES_URL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_place_details,
                container, false);

        mAdapter = new PlaceSlidesFragmentAdapter(getActivity()
                .getSupportFragmentManager());
        mAdapter.setImages(images);

        mPager = (ViewPager) view.findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);

        mIndicator = (CirclePageIndicator) view.findViewById(R.id.indicator);
        mIndicator.setViewPager(mPager);
        ((CirclePageIndicator) mIndicator).setSnap(true);

        mIndicator
                .setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageSelected(int position) {
                    }

                    @Override
                    public void onPageScrolled(int position,
                                               float positionOffset, int positionOffsetPixels) {
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {
                    }
                });
        return view;
    }
    
    public void setImagesResources(Object[] images){
       if(mAdapter!=null) mAdapter.setImages(images);
    }
}
