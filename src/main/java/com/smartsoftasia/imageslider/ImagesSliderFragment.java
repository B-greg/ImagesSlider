package com.smartsoftasia.imageslider;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import com.smartsoftasia.imageslider.viewPagerIndicator.CirclePageIndicator;
import com.smartsoftasia.imageslider.viewPagerIndicator.PageIndicator;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by gregoire barret on 2/12/15.
 * For SmartCommerce project.
 */
public class ImagesSliderFragment extends Fragment implements ImageSlideFragment.OnImageClick {

  public interface OnImageSliderScroll {
    void onPageSelected(int position);

    void onPageScrolled(int position, float positionOffset, int positionOffsetPixels);
  }

  public interface OnClickListener {
    void onClick(int position);
  }

  OnImageSliderScroll imageSliderScrollListener;
  OnClickListener clickListener;

  ImageSlidesFragmentAdapter mAdapter;
  ViewPager mPager;
  PageIndicator mIndicator;
  Boolean indicatorVisibility = true;
  boolean arrowVisibility = true;
  int mCurrentPosition = 0;
  ImageView leftArrow, rightArrow;
  // Object[] images;
  // List<Object> images = new ArrayList<>();

  public static final String TAG = "detailsFragment";
  private static final String ARG_IMAGES_URL = "image_url";

  public static ImagesSliderFragment newInstance() {
    ImagesSliderFragment fragment = new ImagesSliderFragment();
    Bundle args = new Bundle();
    // args.putStringArray(ARG_IMAGES_URL, strings);
    // fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    if (getArguments() != null) {
      // images = getArguments().getStringArray(ARG_IMAGES_URL);
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_image, container, false);
    leftArrow = (ImageView) view.findViewById(R.id.imageView_arrow_left);
    rightArrow = (ImageView) view.findViewById(R.id.imageView_arrow_Right);

    mPager = (ViewPager) view.findViewById(R.id.pager);
    mAdapter = new ImageSlidesFragmentAdapter(getActivity().getSupportFragmentManager());
    mAdapter.setOnImageClickListener(this);
    mIndicator = (CirclePageIndicator) view.findViewById(R.id.indicator);

    updateView();
    ArrowOnClick();

    return view;
  }

  private void updateView() {
    mAdapter.setImages(new ArrayList<Object>());
    mPager.setAdapter(mAdapter);
    mIndicator.setViewPager(mPager);
    ((CirclePageIndicator) mIndicator).setSnap(true);
    mIndicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
      @Override
      public void onPageSelected(int position) {
        mCurrentPosition = position;
        if (imageSliderScrollListener != null) imageSliderScrollListener.onPageSelected(position);
      }

      @Override
      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (imageSliderScrollListener != null) {
          imageSliderScrollListener.onPageScrolled(position, positionOffset, positionOffsetPixels);
        }
      }

      @Override
      public void onPageScrollStateChanged(int state) {
      }
    });
    if (indicatorVisibility) {
      ((CirclePageIndicator) mIndicator).setVisibility(View.VISIBLE);
    } else {
      ((CirclePageIndicator) mIndicator).setVisibility(View.GONE);
    }

    leftArrow.setVisibility(arrowVisibility ? View.VISIBLE : View.GONE);
    rightArrow.setVisibility(arrowVisibility ? View.VISIBLE : View.GONE);
  }

  @Override
  public void onImageClick(View view) {
    if (clickListener != null) clickListener.onClick(mCurrentPosition);
  }

  public void setIndicatorVisibility(Boolean visible) {
    indicatorVisibility = visible;
  }

  public void setArrowVisibility(boolean visibility) {
    arrowVisibility = visibility;
  }

  public void setImagesResources(List<Object> images) {
    if (mAdapter == null) return;
    mAdapter.setImages(images);
    mAdapter.notifyDataSetChanged();
  }

  public void setImageSliderScrollListener(OnImageSliderScroll imageSliderScrollListener) {
    this.imageSliderScrollListener = imageSliderScrollListener;
  }

  public void setOnClickListener(OnClickListener clickListener) {
    this.clickListener = clickListener;
  }

  public void setAutoImageSlider(boolean isAutoSlider, double second, double delay) {
    Timer timer = new Timer();
    if (isAutoSlider) {
      if (second < 1 || delay < 0.1) {
        timer.schedule(new AutoSlider(), 1000, 1000);
      } else {
        timer.schedule(new AutoSlider(), (long) delay * 1000, (long) second * 1000);
      }
    } else {
      timer.cancel();
    }
  }

  public void ArrowOnClick() {
    leftArrow.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mIndicator.setCurrentItem(mCurrentPosition - 1);
      }
    });
    rightArrow.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mIndicator.setCurrentItem(mCurrentPosition + 1);
      }
    });
  }

  Handler handler = new Handler();

  Runnable Update = new Runnable() {
    public void run() {
      if (mCurrentPosition == mAdapter.getCount() - 1) {
        mCurrentPosition = 0;
        mIndicator.setCurrentItem(mCurrentPosition);
      }
      mIndicator.setCurrentItem(mCurrentPosition++);
    }
  };

  public class AutoSlider extends TimerTask {
    @Override
    public void run() {
      handler.post(Update);
    }
  }
}
