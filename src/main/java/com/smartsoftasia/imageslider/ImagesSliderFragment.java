package com.smartsoftasia.imageslider;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smartsoftasia.imageslider.viewPagerIndicator.CirclePageIndicator;
import com.smartsoftasia.imageslider.viewPagerIndicator.PageIndicator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gregoire barret on 2/12/15.
 * For SmartCommerce project.
 */
public class ImagesSliderFragment extends Fragment implements ImageSlideFragment.OnImageClick{



    public interface OnImageSliderScroll{
        public void onPageSelected(int position);
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels);
    }
    public interface OnClickListener{
        public void onClick(int position);
    }


    OnImageSliderScroll imageSliderScrollListener;
    OnClickListener clickListener;
    ImageSlidesFragmentAdapter mAdapter;
    ViewPager mPager;
    PageIndicator mIndicator;
    Boolean indicatorVisibility = true;
    int mCurrentPosition = 0;
    //Object[] images;
   // List<Object> images = new ArrayList<>();

    public static final String TAG = "detailsFragment";
    private static final String ARG_IMAGES_URL = "image_url";
    
    
    public static ImagesSliderFragment newInstance(){
        ImagesSliderFragment fragment = new ImagesSliderFragment();
        Bundle args = new Bundle();
     //   args.putStringArray(ARG_IMAGES_URL, strings);
     //   fragment.setArguments(args);
        return fragment;
        
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
       //     images = getArguments().getStringArray(ARG_IMAGES_URL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image, container, false);

        mAdapter = new ImageSlidesFragmentAdapter(getActivity().getSupportFragmentManager());
        mAdapter.setOnImageClickListener(this);

        mPager = (ViewPager) view.findViewById(R.id.pager);
        mIndicator = (CirclePageIndicator) view.findViewById(R.id.indicator);

        updateView();

        return view;
    }

    private void updateView(){
        mAdapter.setImages(new ArrayList<Object>());


        mPager.setAdapter(mAdapter);

        mIndicator.setViewPager(mPager);
        ((CirclePageIndicator) mIndicator).setSnap(true);

        mIndicator
                .setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageSelected(int position) {
                        mCurrentPosition = position;
                        if(imageSliderScrollListener!=null)imageSliderScrollListener.onPageSelected(position);
                    }

                    @Override
                    public void onPageScrolled(int position,
                                               float positionOffset, int positionOffsetPixels) {
                        if(imageSliderScrollListener!=null)imageSliderScrollListener.onPageScrolled(position,
                                positionOffset,positionOffsetPixels);
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {
                    }
                });
        if(indicatorVisibility){
            ((CirclePageIndicator) mIndicator).setVisibility(View.VISIBLE);
        }else{
            ((CirclePageIndicator) mIndicator).setVisibility(View.GONE);
        }
    }

    @Override
    public void onImageClick(View view) {
        if(clickListener!=null)clickListener.onClick(mCurrentPosition);
    }

    public void setIndicatorVisibility(Boolean visible){
        indicatorVisibility = visible;

    }

    public void setImagesResources(List<Object> images){
        if(mAdapter==null)return;
        mAdapter.setImages(images);
        mAdapter.notifyDataSetChanged();
    }

    public void setImageSliderScrollListener(OnImageSliderScroll imageSliderScrollListener) {
        this.imageSliderScrollListener = imageSliderScrollListener;
    }

    public void setOnClickListener(OnClickListener clickListener) {
        this.clickListener = clickListener;
    }
}
