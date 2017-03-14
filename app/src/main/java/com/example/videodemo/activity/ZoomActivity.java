package com.example.videodemo.activity;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.videodemo.R;
import com.example.videodemo.view.ZoomImageView;

public class ZoomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom);
        ViewPager viewPager= (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new MyAdapter());
    }

    private ImageView[] imageViews=new ImageView[5];

    class MyAdapter extends PagerAdapter{
        @Override
        public int getCount() {
            return imageViews.length;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ZoomImageView zoomImageView=new ZoomImageView(ZoomActivity.this);
            zoomImageView.setImageResource((position%2==1)?R.drawable.test:R.drawable.test1);
            container.addView(zoomImageView);
            imageViews[position]=zoomImageView;
            return zoomImageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(imageViews[position]);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }
    }
}
