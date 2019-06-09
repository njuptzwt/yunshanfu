package com.example.yunshanfu.Activity;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.yunshanfu.Fragment.IndexFragment;
import com.example.yunshanfu.R;

/**
 * 主界面Activity，可以加载同一个页面的三种不同的Fragment页面
 */
public class FrameLayoutActivity extends AppCompatActivity {

    // 云闪付中间页面，使用的Fragment(需要使用Fragment中嵌套Fragment),首页
    IndexFragment indexFragment = new IndexFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_layout);
        // 添加Banner_Fragment到Activity中
        FragmentManager fragmentManager = getSupportFragmentManager();
        // 由fragment manager管理
        // 动态添加fragment中
        fragmentManager.beginTransaction().add(R.id.main_fragment_1, indexFragment).commit();
    }
}
