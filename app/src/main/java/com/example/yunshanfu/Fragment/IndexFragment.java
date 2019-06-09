package com.example.yunshanfu.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.yunshanfu.Adapter.ItemAdapter;
import com.example.yunshanfu.Adapter.TabPagerAdapter;
import com.example.yunshanfu.Model.Item;
import com.example.yunshanfu.R;
import java.util.ArrayList;
import java.util.List;

public class IndexFragment extends Fragment {


    // SearchView的搜索框
    private SearchView searchView;
    // GridView视图，静态指定
    private RecyclerView recyclerView;
    private List<Item> itemList = new ArrayList<>();
    // 轮播图fragment
    FragmentBanner fragmentBanner = new FragmentBanner();
    // 配置TabLayout+ViewPager+Fragment
    private TabLayout tabLayout;
    private List<Fragment> fragments = new ArrayList<>();
    ViewPager viewPager;
    private List<Item> tabItemList = new ArrayList<>();
    //

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 获取index_fragment的布局,初始化布局
        View view = inflater.inflate(R.layout.index_fragment, container, false);
        tabLayout = view.findViewById(R.id.tab_layout);
        viewPager = view.findViewById(R.id.content_vp);
        recyclerView=view.findViewById(R.id.RC1);
        initItems();
        // 配置SearchView相关
        searchView = view.findViewById(R.id.sv1);
        searchView.setQueryHint("请输入商品号");
        searchView.setIconifiedByDefault(false);
        // 设置SerachView失去焦点
        searchView.setFocusable(false);
        // 布局赋值，8个GridView
        GridLayoutManager layoutManager = new GridLayoutManager(container.getContext(), 4);
        recyclerView.setLayoutManager(layoutManager);
        ItemAdapter itemAdapter = new ItemAdapter(itemList);
        recyclerView.setAdapter(itemAdapter);
        // 轮播图的fragment配置
        FragmentManager fragmentManager = getChildFragmentManager();
        fragmentManager.beginTransaction().add(R.id.banner_fragment,fragmentBanner).commit();
        // TabLayout的配置
        tabLayout.addTab(tabLayout.newTab().setText("热门活动"));
        tabLayout.addTab(tabLayout.newTab().setText("好优惠"));
        tabLayout.addTab(tabLayout.newTab().setText("权益精选"));
        init_List();
        fragments.add(RecyclerViewFragment.newInstace(tabItemList));
        fragments.add(RecyclerViewFragment.newInstace(tabItemList));
        fragments.add(RecyclerViewFragment.newInstace(tabItemList));
        // 绑定适配器
        TabPagerAdapter tabPagerAdapter = new TabPagerAdapter(getChildFragmentManager());
        tabPagerAdapter.setTabFragments(fragments);
        viewPager.setAdapter(tabPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }

    /**
     * 静态初始化Tablayout的初始化数据
     */
    public void init_List() {
        Item item1 = new Item();
        item1.setDescription("公交随即立减20元");
        item1.setImageId(R.drawable.tab1);
        tabItemList.add(item1);
        Item item2 = new Item();
        item2.setDescription("一折爽爽购");
        item2.setImageId(R.drawable.tab2);
        tabItemList.add(item2);
        Item item3 = new Item();
        item3.setImageId(R.drawable.tab3);
        item3.setDescription("福利时间");
        tabItemList.add(item3);
        Item item4 = new Item();
        item4.setDescription("来伊份打折");
        item4.setImageId(R.drawable.tab4);
        tabItemList.add(item4);
        Item item5 = new Item();
        item5.setDescription("公交随即立减20元");
        item5.setImageId(R.drawable.tab1);
        tabItemList.add(item5);
        Item item6 = new Item();
        item6.setImageId(R.drawable.tab2);
        item6.setDescription("公交随即立减20元");
        tabItemList.add(item6);
        Item item7 = new Item();
        item7.setImageId(R.drawable.tab3);
        item7.setDescription("一折爽爽购");
        tabItemList.add(item7);
        Item item8 = new Item();
        item8.setImageId(R.drawable.tab4);
        item8.setDescription("来伊份打折");
        tabItemList.add(item8);
    }
    /**
     * 静态初始化GridLayout的初始组件
     */
    private void initItems() {
        Item item1 = new Item();
        item1.setDescription("乘车码");
        item1.setImageId(R.drawable.img_guide_middle_icon1);
        itemList.add(item1);
        Item item2 = new Item();
        item2.setDescription("转账");
        item2.setImageId(R.drawable.img_guide_middle_icon2);
        itemList.add(item2);
        Item item3 = new Item();
        item3.setImageId(R.drawable.img_guide_middle_icon3);
        item3.setDescription("信用卡还款");
        itemList.add(item3);
        Item item4 = new Item();
        item4.setDescription("QQ充值");
        item4.setImageId(R.drawable.ic_qq_pressed);
        itemList.add(item4);
        Item item5 = new Item();
        item5.setDescription("公共缴费");
        item5.setImageId(R.drawable.ic_qzone_pressed);
        itemList.add(item5);
        Item item6 = new Item();
        item6.setImageId(R.drawable.ic_weibo);
        item6.setDescription("QQ信贷");
        itemList.add(item6);
        Item item7 = new Item();
        item7.setImageId(R.drawable.ic_weixin);
        item7.setDescription("微信理财");
        itemList.add(item7);
        Item item8 = new Item();
        item8.setImageId(R.drawable.img_guide_1);
        item8.setDescription("更多");
        itemList.add(item8);
    }
}
