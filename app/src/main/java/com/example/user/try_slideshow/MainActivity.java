package com.example.user.try_slideshow;

import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ViewPager slideViewPager;
    SlideShowPagerAdapter slideShowPagerAdapter;
    ArrayList<ImageModel> mImages;
    Handler handler;
    int MSG_SHOW_SLIDE = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImages = new ArrayList<>();
        mImages.add(new ImageModel("https://wallpaperscraft.com/image/kitten_ginger_baby_lie_1938_1280x720.jpg"));
        mImages.add(new ImageModel("http://animalli.com/wp-content/uploads/2016/11/baby-animals-cat-kittens-cats-kitten-cute-incredibly-1900x1080.jpg"));
        mImages.add(new ImageModel("https://wallpaperscraft.com/image/cat_kitten_paws_muzzle_eyes_striped_1503_1280x720.jpg"));

        slideShowPagerAdapter = new SlideShowPagerAdapter(this,mImages);

        slideViewPager = (ViewPager) findViewById(R.id.slideShowPager);
        slideViewPager.setAdapter(slideShowPagerAdapter);

        //Bind the title indicator to the adapter
        CirclePageIndicator circlePageIndicator = (CirclePageIndicator) findViewById(R.id.slideIndicator);
        circlePageIndicator.setViewPager(slideViewPager);

        //auto slide
        handler=new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                if(slideViewPager.getCurrentItem() >= mImages.size()-1) {
                    handler.removeMessages(MSG_SHOW_SLIDE);
                    slideViewPager.setCurrentItem(0);
                }else{
                    handler.removeMessages(MSG_SHOW_SLIDE);
                    slideViewPager.setCurrentItem(slideViewPager.getCurrentItem()+1);
                }
                sendEmptyMessageDelayed(MSG_SHOW_SLIDE,7000);
            }
        };
    }

    @Override
    public void onResume() {
        super.onResume();
        //start the slideshow
        handler.sendEmptyMessage(MSG_SHOW_SLIDE);
    }


}
