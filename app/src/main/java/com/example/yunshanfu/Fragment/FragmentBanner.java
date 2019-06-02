package com.example.yunshanfu.Fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.yunshanfu.Adapter.CarouselPagerAdapter;
import com.example.yunshanfu.R;
import com.example.yunshanfu.UI.CarouselViewPager;

import java.util.ArrayList;
import java.util.List;

/**
   生成Fragement,用于轮播图
 */
public class FragmentBanner extends Fragment implements ViewPager.OnPageChangeListener {
    private CarouselViewPager mCarouselView;
    private List<ImageView> ivList = new ArrayList<ImageView>();
    private int[] ivIds = {R.drawable.bg_my_banner, R.drawable.second_floor,
            R.drawable.bg_my_banner, R.drawable.second_floor};

    private ImageView[] indicationPoint;//指示点控件
    private LinearLayout pointLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_banner, container, false);
        initViews(rootView);
        initData();
        return rootView;
    }

    private void initViews(View rootView) {
        mCarouselView = (CarouselViewPager) rootView.findViewById(R.id.mCarouselView);
        pointLayout = (LinearLayout) rootView.findViewById(R.id.pointLayout);
    }

    private void initData() {
        for (int i = 0; i < ivIds.length; i++) {
            ImageView iv = new ImageView(getActivity());
            //得到资源文件的BitMap
            Bitmap image = BitmapFactory.decodeResource(getResources(), ivIds[i]);
           //创建RoundedBitmapDrawable对象
            RoundedBitmapDrawable roundImg = RoundedBitmapDrawableFactory.create(getResources(), image);
            //抗锯齿
            roundImg.setAntiAlias(true);
           //设置圆角半径
            roundImg.setCornerRadius(100);
           //设置显示图片
            iv.setImageDrawable(roundImg);

            //iv.setImageResource(ivIds[i]);
            ivList.add(iv);
        }

        indicationPoint = new ImageView[ivList.size()];
        for (int i = 0; i < indicationPoint.length; i++) {
            ImageView point = new ImageView(getActivity());
            LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(10, 10);
            layout.setMargins(10, 0, 10, 0);
            point.setLayoutParams(layout);

            indicationPoint[i] = point;
            if (i == 0) {
                indicationPoint[i].setBackgroundResource(R.drawable.page_indicator_focused);
            } else {
                indicationPoint[i].setBackgroundResource(R.drawable.page_indicator_unfocused);
            }
            pointLayout.addView(point);
        }

        mCarouselView.setAdapter(new CarouselPagerAdapter(ivList));
        mCarouselView.addOnPageChangeListener(this);
        mCarouselView.setDisplayTime(2000);
        mCarouselView.start();

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setPointColor(position % ivList.size());

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void setPointColor(int selectItem) {
        for (int i = 0; i < indicationPoint.length; i++) {
            if (i == selectItem) {
                indicationPoint[i].setBackgroundResource(R.drawable.page_indicator_focused);
            } else {
                indicationPoint[i].setBackgroundResource(R.drawable.page_indicator_unfocused);
            }

        }
    }


}
