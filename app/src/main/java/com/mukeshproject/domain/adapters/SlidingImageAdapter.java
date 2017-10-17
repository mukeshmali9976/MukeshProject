package com.mukeshproject.domain.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mukeshproject.R;
import com.mukeshproject.models.SlidingImageModel;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;

/**
 * Created by lenovo pc on 27/08/2017.
 */

public class SlidingImageAdapter extends PagerAdapter  {

    private ArrayList<SlidingImageModel> imageModelArrayList;
    private LayoutInflater inflater;
    private Context context;
    private ImageLoader mImageLoader;
    private DisplayImageOptions options;

    public SlidingImageAdapter(Context context, ArrayList<SlidingImageModel> imageModelArrayList) {
        this.context = context;
        this.imageModelArrayList = imageModelArrayList;
        inflater = LayoutInflater.from(context);

        mImageLoader = ImageLoader.getInstance();
        mImageLoader.init(ImageLoaderConfiguration.createDefault(context));
        options = new DisplayImageOptions.Builder()
                .showStubImage(R.drawable.img2)
                .showImageForEmptyUri(R.drawable.img3)
                .showImageOnFail(R.drawable.img2)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return imageModelArrayList.size();
    }
    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View imageLayout = inflater.inflate(R.layout.layout_sliding_images, view, false);
        ImageView imageView = (ImageView) imageLayout.findViewById(R.id.image1);


        mImageLoader.displayImage("https://www.eazylo.com/assets1/images/slider/" + imageModelArrayList.get(position).getSliderImage(), imageView, options);

        view.addView(imageLayout);
        return imageLayout;

    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }
}

