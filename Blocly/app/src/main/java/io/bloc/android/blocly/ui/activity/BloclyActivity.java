package io.bloc.android.blocly.ui.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import io.bloc.android.blocly.R;
import io.bloc.android.blocly.ui.adapter.ItemAdapter;

/**
 * Created by Kari on 10/5/2015.
 */
public class BloclyActivity extends Activity implements ImageLoadingListener{

    private ItemAdapter itemAdapter;
    ImageView background;
    static final String backgroundHttp = "http://www.hdwallpapers.in/wallpapers/cute_baby_boy_mobile-1920x1440.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blocly);

//      download image for background
        background =  (ImageView)findViewById(R.id.iv_background);
        ImageLoader.getInstance().loadImage(backgroundHttp , this);

        itemAdapter = new ItemAdapter();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_activity_blocly);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(itemAdapter);
    }

    @Override
    public void onLoadingStarted(String imageUri, View view) {

    }

    @Override
    public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
    }

    @Override
    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {

        // show the loaded image
        background.setImageBitmap(loadedImage);
        background.setVisibility(View.VISIBLE);
    }
    @Override
    public void onLoadingCancelled(String imageUri, View view){
        // Attempt retry
        ImageLoader.getInstance().loadImage(imageUri, this);
    }
}


