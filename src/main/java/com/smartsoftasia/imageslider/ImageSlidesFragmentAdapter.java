package com.smartsoftasia.imageslider;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.smartsoftasia.imageslider.viewPagerIndicator.IconPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gregoire barret on 2/12/15.
 * For SmartCommerce project.
 */
public class ImageSlidesFragmentAdapter extends FragmentStatePagerAdapter implements IconPagerAdapter {
    public static final String TAG = "PlaceSlidesFragmentAdapter";

    private List<Object> Images = new ArrayList<>(); /*= new int[] { R.drawable.image1, R.drawable.image2,
            R.drawable.image3, R.drawable.image4
    };*/

    protected static final int[] ICONS = new int[] { R.drawable.vpi__tab_indicator,
            R.drawable.vpi__tab_indicator, R.drawable.vpi__tab_indicator, R.drawable.vpi__tab_indicator };

    private int mCount = 0;

    ImageSlideFragment.OnImageClick onImageClickListener;

    public ImageSlidesFragmentAdapter(FragmentManager fm) {
        super(fm);
    }
    
    public void setImages(List<Object> images){
        this.Images.clear();
        this.Images.addAll(images);
    }

    @Override
    public Fragment getItem(int position) {
        ImageSlideFragment fragment = new ImageSlideFragment();
        if(Images!=null){
            if(Images.get(position) instanceof Integer){
                fragment.setResource((int) Images.get(position));
            }else if(Images.get(position) instanceof String){
                fragment.setUrl((String)Images.get(position));
            }else if(Images.get(position) instanceof ImageSlider){
                fragment.setImageSlider((ImageSlider)Images.get(position));
            }
        }
        fragment.setOnImageClickListener(onImageClickListener);
        return fragment;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public int getCount() {
        return Images==null? 0 : Images.size();
    }

    @Override
    public int getIconResId(int index) {
        return ICONS[index % ICONS.length];
    }

    public void setCount(int count) {
        if (count > 0 && count <= 10) {
            mCount = count;
            notifyDataSetChanged();
        }
    }

    public void setOnImageClickListener(ImageSlideFragment.OnImageClick onImageClickListener) {
        this.onImageClickListener = onImageClickListener;
    }
}
