package com.smartsoftasia.imageslider;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

/**
 * Created by gregoire barret on 2/12/15.
 * For SmartCommerce project.
 */
public class PlaceSlideFragment extends Fragment {
    public static final String TAG = "PlaceSlideFragment";
    
    private static final int TYPE_RESSOURCE = 1;
    private static final int TYPE_URL = 2;
    
    int ressouceType;
    int imageResourceId;
    String imageUrl;
    
    public PlaceSlideFragment() {
    }
    
    public void setResource(int i){
        ressouceType = TYPE_RESSOURCE;
        imageResourceId = i;
    }
    public void setUrl(String url){
        ressouceType=TYPE_URL;
        imageUrl = url;
        
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ImageView image = new ImageView(getActivity());


        LinearLayout layout = new LinearLayout(getActivity());
        layout.setLayoutParams(new WindowManager.LayoutParams());

        layout.setGravity(Gravity.CENTER);
        layout.addView(image);

        if(ressouceType==TYPE_RESSOURCE) {
            image.setImageResource(imageResourceId);
        }
        else if (ressouceType==TYPE_URL) {
            Picasso.with(getActivity()).load(imageUrl).into(image);
        }

        return layout;
    }
}
