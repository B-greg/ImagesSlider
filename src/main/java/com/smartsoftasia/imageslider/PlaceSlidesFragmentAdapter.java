package com.smartsoftasia.imageslider;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.smartsoftasia.imageslider.viewPagerIndicator.IconPagerAdapter;

/**
 * Created by gregoire barret on 2/12/15.
 * For SmartCommerce project.
 */
public class PlaceSlidesFragmentAdapter extends FragmentPagerAdapter implements IconPagerAdapter {
    public static final String TAG = "PlaceSlidesFragmentAdapter";

    private Object[] Images; /*= new int[] { R.drawable.image1, R.drawable.image2,
            R.drawable.image3, R.drawable.image4
    };*/

    protected static final int[] ICONS = new int[] { R.drawable.vpi__tab_indicator,
            R.drawable.vpi__tab_indicator, R.drawable.vpi__tab_indicator, R.drawable.vpi__tab_indicator };

    private int mCount = 0;

    public PlaceSlidesFragmentAdapter(FragmentManager fm) {
        super(fm);
    }
    
    public void setImages(Object[] images){
        this.Images = images;
    }

    @Override
    public Fragment getItem(int position) {
        PlaceSlideFragment fragment = new PlaceSlideFragment();
        if(Images!=null){
            if(Images[position] instanceof Integer){
                fragment.setResource((int)Images[position]);
            }else if(Images[position] instanceof String){
                fragment.setUrl((String)Images[position]);
            }
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return Images==null? 0 : Images.length;
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
}
