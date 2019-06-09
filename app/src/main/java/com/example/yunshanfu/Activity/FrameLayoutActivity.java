package com.example.yunshanfu.Activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.yunshanfu.Fragment.BlankFragment;
import com.example.yunshanfu.Fragment.IndexFragment;
import com.example.yunshanfu.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 主界面Activity，可以加载同一个页面的三种不同的Fragment页面
 */
public class FrameLayoutActivity extends AppCompatActivity {

    // 云闪付中间页面，使用的Fragment(需要使用Fragment中嵌套Fragment),首页
    IndexFragment indexFragment = new IndexFragment();
    private ViewPager mViewPager;
    private RadioGroup mTabRadioGroup;
    private List<Fragment> mFragments;
    private FragmentPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_layout);
        initView();
    }

    private void initView() {
        // find view
        mViewPager = findViewById(R.id.fragment_vp);
        mTabRadioGroup = findViewById(R.id.tabs_rg);
        // init fragment
        mFragments = new ArrayList<>(3);
        mFragments.add(BlankFragment.newInstance("收付款"));
        mFragments.add(indexFragment);
        mFragments.add(BlankFragment.newInstance("卡管理"));
        // init view pager
        mAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), mFragments);
        mViewPager.setAdapter(mAdapter);
        // register listener
        mViewPager.addOnPageChangeListener(mPageChangeListener);
        // 设置初始页
        getInitFragment();
        mTabRadioGroup.setOnCheckedChangeListener(mOnCheckedChangeListener);
    }

    // get init Fragment,简单粗暴直接设值
    private void getInitFragment() {
        mViewPager.setCurrentItem(1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mViewPager.removeOnPageChangeListener(mPageChangeListener);
    }

    // 双相关联，联动。RadioButton按键带动ViewPager变动
    private ViewPager.OnPageChangeListener mPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            RadioButton radioButton = (RadioButton) mTabRadioGroup.getChildAt(position);
            radioButton.setChecked(true);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    // 按键联动
    private RadioGroup.OnCheckedChangeListener mOnCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            for (int i = 0; i < group.getChildCount(); i++) {
                if (group.getChildAt(i).getId() == checkedId) {
                    mViewPager.setCurrentItem(i);
                    return;
                }
            }
        }
    };

    // Adapter适配
    private class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> mList;

        public MyFragmentPagerAdapter(FragmentManager fm, List<Fragment> list) {
            super(fm);
            this.mList = list;
        }

        @Override
        public Fragment getItem(int position) {
            return this.mList == null ? null : this.mList.get(position);
        }

        @Override
        public int getCount() {
            return this.mList == null ? 0 : this.mList.size();
        }
    }

}
