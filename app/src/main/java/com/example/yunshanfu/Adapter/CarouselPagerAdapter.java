package com.example.yunshanfu.Adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * 新的Adapter绑定视图扩容
 */
public class CarouselPagerAdapter extends PagerAdapter {

    private List<ImageView> ivList;

    public CarouselPagerAdapter(List<ImageView> ivList) {
        this.ivList = ivList;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(ivList.get(position % ivList.size()));
        return ivList.get(position % ivList.size());
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(ivList.get(position % ivList.size()));
    }

    @Override
    public int getCount() {
        return ivList == null ? 0 : Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
