package com.example.yunshanfu.Fragment;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.yunshanfu.R;

import static com.example.yunshanfu.Fragment.FuKuanFragment.createQRCodeBitmap;

public class ShouKuanFragment extends Fragment {

    private ImageView imageView1;

    private ImageView image;

    private Dialog d1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_shoukuan, container, false);
        imageView1 = view.findViewById(R.id.QRcode_shoukuan);
        Bitmap logoBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_small);
        Bitmap b1 = createQRCodeBitmap("hello world", 400, 400,
                "UTF-8", "H", "1",
                Color.BLACK, Color.WHITE, logoBitmap, 0.15f);
        imageView1.setImageBitmap(b1);
        // 设置imageView的点击响应事件，直接把imageView传递给Dialog1其实就可以了
        initDialog1();
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // v.getRootView().setVisibility(View.INVISIBLE);
                d1.show();
            }
        });

        return view;
    }

    private void initDialog1() {
        d1 = new Dialog(getContext(), R.style.FullActivity);
        // dialog设置为全屏
        WindowManager.LayoutParams attributes = d1.getWindow().getAttributes();
        attributes.width = WindowManager.LayoutParams.MATCH_PARENT;
        attributes.height = WindowManager.LayoutParams.MATCH_PARENT;
        d1.getWindow().setAttributes(attributes);
        // 设置image的宽度和高度，在dialog里面
        image = getImageView();
        ViewGroup.MarginLayoutParams margin = new ViewGroup.MarginLayoutParams(image.getLayoutParams());
        margin.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        margin.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        // 设置的是像素值，能否转化为dp值，需要改进
        margin.setMargins(150, 600, 150, 100);
        d1.setContentView(image, margin);
        //d1.setContentView(image,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        //大图的点击事件（点击让他消失）
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // v.getRootView().setVisibility(View.VISIBLE);
                d1.dismiss();
            }
        });
    }

    private ImageView getImageView() {
        ImageView imageView = new ImageView(getContext());
        //宽高
        imageView.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        Bitmap logoBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_small);
        Bitmap b1 = createQRCodeBitmap("hello world", 800, 800,
                "UTF-8", "H", "1",
                Color.BLACK, Color.WHITE, logoBitmap, 0.15f);
        imageView.setImageBitmap(b1);
        return imageView;
    }
}
