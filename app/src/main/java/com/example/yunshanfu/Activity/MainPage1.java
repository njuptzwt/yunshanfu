package com.example.yunshanfu.Activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yunshanfu.Adapter.ItemAdapter;
import com.example.yunshanfu.Adapter.TabPagerAdapter;
import com.example.yunshanfu.Fragment.FragmentBanner;
import com.example.yunshanfu.Fragment.RecyclerViewFragment;
import com.example.yunshanfu.Model.Item;
import com.example.yunshanfu.R;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static com.example.yunshanfu.Utils.DensityUtils.dip2px;

/**
 * 首页的Activity,银联首页的Activity
 */
public class MainPage1 extends AppCompatActivity {

    private SearchView searchView;

    private List<Item> itemList = new ArrayList<>();

    private RecyclerView recyclerView;

    private FragmentBanner fragmentBanner = new FragmentBanner();

    // 配置tablayou和pageview和fragment
    private TabLayout tabLayout;

    private List<Fragment> fragments = new ArrayList<>();

    ViewPager viewPager;

    private List<Item> tabItemList = new ArrayList<>();
    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page1);
        // 配置searchView
        searchView = findViewById(R.id.sv1);
        searchView.setQueryHint("请输入商品号");
        searchView.setIconifiedByDefault(false);
        // 设置SerachView失去焦点
        searchView.setFocusable(false);
        // 配置GridView相关
        initItems();
        recyclerView = findViewById(R.id.Rc1);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 4);
        recyclerView.setLayoutManager(layoutManager);
        ItemAdapter itemAdapter = new ItemAdapter(itemList);
        recyclerView.setAdapter(itemAdapter);

        // 添加Banner_Fragment到Activity中
        FragmentManager fragmentManager = getSupportFragmentManager();
        // 由fragment manager管理
        // 动态添加fragment中
        fragmentManager.beginTransaction().add(R.id.main_fragment, fragmentBanner).commit();

        //添加tablayout相关
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.content_vp);
        tabLayout.addTab(tabLayout.newTab().setText("热门活动"));
        tabLayout.addTab(tabLayout.newTab().setText("好优惠"));
        tabLayout.addTab(tabLayout.newTab().setText("权益精选"));

        init_List();
        fragments.add(RecyclerViewFragment.newInstace(tabItemList));
        fragments.add(RecyclerViewFragment.newInstace(tabItemList));
        fragments.add(RecyclerViewFragment.newInstace(tabItemList));
        Log.e("TextFragment", "init");
        viewPager = findViewById(R.id.content_vp);

        // 绑定适配器
        TabPagerAdapter tabPagerAdapter = new TabPagerAdapter(getSupportFragmentManager());
        tabPagerAdapter.setTabFragments(fragments);
        viewPager.setAdapter(tabPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        // 函数无效，直接用配置的属性有效
        tabLayout=reflex(tabLayout);
    }

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

    // 设置TabLayout的Indicator的宽度为自适应Text的长度，阅读TabLayout的源码
    // 要求对组件的源码和属性熟悉
    public TabLayout reflex(final TabLayout tabLayout) {
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                try {
                    //拿到tabLayout的mTabStrip属性
                    LinearLayout mTabStrip = (LinearLayout) tabLayout.getChildAt(0);

                    int dp10 = dip2px(tabLayout.getContext(), 10);

                    for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                        View tabView = mTabStrip.getChildAt(i);

                        //拿到tabView的mTextView属性  tab的字数不固定一定用反射取mTextView
                        Field mTextViewField = tabView.getClass().getDeclaredField("mTextView");
                        mTextViewField.setAccessible(true);
                        TextView mTextView = (TextView) mTextViewField.get(tabView);
                        tabView.setPadding(0, 0, 0, 0);
                        //因为我想要的效果是   字多宽线就多宽，所以测量mTextView的宽度
                        int width = 0;
                        width = mTextView.getWidth();
                        if (width == 0) {
                            mTextView.measure(0, 0);
                            width = mTextView.getMeasuredWidth();
                        }
                        //设置tab左右间距为10dp  注意这里不能使用Padding 因为源码中线的宽度是根据 tabView的宽度来设置的
                        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tabView.getLayoutParams();
                        params.width = width;
                        params.leftMargin = dp10;
                        params.rightMargin = dp10;
                        tabView.setLayoutParams(params);
                        tabView.invalidate();
                    }

                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });
        return tabLayout;
    }
}
