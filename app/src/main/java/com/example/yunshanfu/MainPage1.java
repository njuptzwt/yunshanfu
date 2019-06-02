package com.example.yunshanfu;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;

import com.example.yunshanfu.Fragment.FragmentBanner;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页的Activity,银联首页的Activity
 */
public class MainPage1 extends AppCompatActivity {

    private SearchView searchView;

    private List<Item> itemList = new ArrayList<>();

    private RecyclerView recyclerView;

    private FragmentBanner fragmentBanner=new FragmentBanner();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page1);
        // 配置searchView
        searchView = findViewById(R.id.sv1);
        searchView.setQueryHint("请输入商品号");
        searchView.setIconifiedByDefault(false);
        // 配置GridView相关
        initItems();
        recyclerView = findViewById(R.id.Rc1);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 4);
        recyclerView.setLayoutManager(layoutManager);
        ItemAdapter itemAdapter = new ItemAdapter(itemList);
        recyclerView.setAdapter(itemAdapter);

        // 添加Fragment到Activity中
        FragmentManager fragmentManager = getSupportFragmentManager();
        // 由fragment manager管理
        // 动态添加fragment中
        fragmentManager.beginTransaction().add(R.id.main_fragment, fragmentBanner).commit();
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
}
