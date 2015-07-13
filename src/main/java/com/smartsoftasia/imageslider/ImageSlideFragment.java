package com.smartsoftasia.imageslider;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by gregoire barret on 2/12/15.
 * For SmartCommerce project.
 */
public class ImageSlideFragment extends Fragment {
    public static final String TAG = "PlaceSlideFragment";

    public interface OnImageClick{
        public void onImageClick(View view);
    }

    public static final String EXTRA_IMAGE = "PlaceSlideFragment:image";
    
    private static final int TYPE_RESSOURCE = 1;
    private static final int TYPE_URL = 2;
    private static final int TYPE_IMAGE_SLIDER = 3;

    int ressouceType;
    int imageResourceId;
    String imageUrl;
    ImageSlider imageSlider;
    OnImageClick onImageClickListener;
    
    public ImageSlideFragment() {
    }
    
    public void setResource(int i){
        ressouceType = TYPE_RESSOURCE;
        imageResourceId = i;
    }
    public void setUrl(String url){
        ressouceType=TYPE_URL;
        imageUrl = url;
        
    }

    public void setImageSlider(ImageSlider is){
        ressouceType = TYPE_IMAGE_SLIDER;
        imageSlider = is;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.image_slider, container, false);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onImageClickListener!=null)onImageClickListener.onImageClick(view);
            }
        });
        
        ImageView image = (ImageView) view.findViewById(R.id.imageView_slider);
        TextView imageName = (TextView) view.findViewById(R.id.textView_slider);

        ViewCompat.setTransitionName(image, EXTRA_IMAGE);

        if(ressouceType==TYPE_RESSOURCE) {
            image.setImageResource(imageResourceId);
            imageName.setVisibility(View.GONE);
        }
        else if (ressouceType==TYPE_URL) {
            Picasso.with(getActivity()).load(imageUrl).into(image);
            imageName.setVisibility(View.GONE);
        }
        else if (ressouceType == TYPE_IMAGE_SLIDER){
            Picasso.with(getActivity()).load(imageSlider.url).into(image);
            imageName.setVisibility(View.VISIBLE);
            imageName.setText(imageSlider.name);
        }

        return view;
    }

    public void setOnImageClickListener(OnImageClick onImageClickListener) {
        this.onImageClickListener = onImageClickListener;
    }
}
