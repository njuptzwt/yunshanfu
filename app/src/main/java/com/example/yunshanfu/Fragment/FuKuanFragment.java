package com.example.yunshanfu.Fragment;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.yunshanfu.R;
import com.example.yunshanfu.Utils.CodeUtils;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.util.Hashtable;

public class FuKuanFragment extends Fragment {

    // 初始化相关组件
    private ImageView imageView1;

    private ImageView imageView2;

    private ImageView image;

    private Dialog d1;

    private Dialog d2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fukuan, container, false);
        imageView1=view.findViewById(R.id.QRcode11);
        imageView2=view.findViewById(R.id.QRcode21);
        Bitmap logoBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_small);
        Bitmap b1 = createQRCodeBitmap("hello world", 400, 400,
                "UTF-8", "H", "1",
                Color.BLACK, Color.WHITE, logoBitmap, 0.15f);
        imageView2.setImageBitmap(b1);
        // 设置imageView的点击响应事件，直接把imageView传递给Dialog1其实就可以了
        initDialog1();
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // v.getRootView().setVisibility(View.INVISIBLE);
                d1.show();
            }
        });

        // 生成条形码相关
        Bitmap barCode = CodeUtils.creatBarcode("134567893454537654", 1000, 300);
        imageView1.setImageBitmap(barCode);
        // 条形码变化相关
        initDialog2();
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d2.show();
            }
        });
        return view;
    }

    // Dialog1初始化,二维码的dialog
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

    // Dialog2 init(),条形码的dialog
    private void initDialog2() {
        d2 = new Dialog(getContext(), R.style.FullActivity);
        // dialog设置为全屏
        WindowManager.LayoutParams attributes = d2.getWindow().getAttributes();
        attributes.width = WindowManager.LayoutParams.MATCH_PARENT;
        attributes.height = WindowManager.LayoutParams.MATCH_PARENT;
        d1.getWindow().setAttributes(attributes);
        RelativeLayout relativeLayout = new RelativeLayout(getContext());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        //将以上的属性赋给LinearLayout
        relativeLayout.setLayoutParams(layoutParams);
        //实例化两个个TextView
        TextView tv = new TextView(getContext());
        TextView tv1 = new TextView(getContext());
        //设置宽高以及权重
        RelativeLayout.MarginLayoutParams tvParams = new RelativeLayout.MarginLayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        //设置textview垂直居中
        tvParams.setMargins(10, 850, 10, 100);
        tv.setRotation(90);
        tv.setLayoutParams(tvParams);
        tv.setText("134567893454537654");
        tv.setTextSize(20);
        relativeLayout.addView(tv);
        // 加载条形码
        ImageView barcode = getImageView1();
        barcode.setRotation(90);
        RelativeLayout.MarginLayoutParams rv = new RelativeLayout.MarginLayoutParams(3000, 300);
        rv.setMargins(10, 830, 100, 100);
        barcode.setLayoutParams(rv);
        barcode.setScaleType(ImageView.ScaleType.FIT_XY);
        barcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d2.dismiss();
            }
        });
        relativeLayout.addView(barcode);
        // tv1设置
        RelativeLayout.MarginLayoutParams tvParams1 = new RelativeLayout.MarginLayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        //设置textview垂直居中
        tvParams1.setMargins(200, 850, 10, 100);
        tv1.setRotation(90);
        tv1.setLayoutParams(tvParams);
        tv1.setText("该付款码只适合于给商家付款时出示");
        tv1.setTextSize(20);
        tv1.setTextColor(Color.YELLOW);
        // relativeLayout.addView(tv1);
        d2.setContentView(relativeLayout);
    }

    //动态加载条形码
    private ImageView getImageView1() {
        ImageView imageView = new ImageView(getContext());
        //宽高
        Bitmap b1 = CodeUtils.creatBarcode("134567893454537654", 6000, 2000);
        imageView.setImageBitmap(b1);
        return imageView;
    }

    // 动态加载二维码
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
    /**
     * @param content                字符串内容
     * @param width                  二维码宽度
     * @param height                 二维码高度
     * @param character_set          编码方式（一般使用UTF-8）
     * @param error_correction_level 容错率 L：7% M：15% Q：25% H：35%
     * @param margin                 空白边距（二维码与边框的空白区域）
     * @param color_black            黑色色块
     * @param color_white            白色色块
     * @param logoBitmap             logo图片
     * @param logoPercent            logo所占百分比
     * @return
     */
    public static Bitmap createQRCodeBitmap(String content, int width, int height, String character_set,
                                            String error_correction_level, String margin, int color_black,
                                            int color_white, Bitmap logoBitmap, float logoPercent) {
        // 字符串内容判空
        if (TextUtils.isEmpty(content)) {
            return null;
        }
        // 宽和高>=0
        if (width < 0 || height < 0) {
            return null;
        }
        try {
            /** 1.设置二维码相关配置,生成BitMatrix(位矩阵)对象 */
            Hashtable<EncodeHintType, String> hints = new Hashtable<>();
            // 字符转码格式设置
            if (!TextUtils.isEmpty(character_set)) {
                hints.put(EncodeHintType.CHARACTER_SET, character_set);
            }
            // 容错率设置
            if (!TextUtils.isEmpty(error_correction_level)) {
                hints.put(EncodeHintType.ERROR_CORRECTION, error_correction_level);
            }
            // 空白边距设置
            if (!TextUtils.isEmpty(margin)) {
                hints.put(EncodeHintType.MARGIN, margin);
            }
            /** 2.将配置参数传入到QRCodeWriter的encode方法生成BitMatrix(位矩阵)对象 */
            BitMatrix bitMatrix = new QRCodeWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);

            /** 3.创建像素数组,并根据BitMatrix(位矩阵)对象为数组元素赋颜色值 */
            int[] pixels = new int[width * height];
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    //bitMatrix.get(x,y)方法返回true是黑色色块，false是白色色块
                    if (bitMatrix.get(x, y)) {
                        pixels[y * width + x] = color_black;//黑色色块像素设置
                    } else {
                        pixels[y * width + x] = color_white;// 白色色块像素设置
                    }
                }
            }

            /** 4.创建Bitmap对象,根据像素数组设置Bitmap每个像素点的颜色值,并返回Bitmap对象 */
            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, width, 0, 0, width, height);

            /** 5.为二维码添加logo图标 */
            if (logoBitmap != null) {
                return addLogo(bitmap, logoBitmap, logoPercent);
            }
            return bitmap;
        } catch (WriterException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 向二维码中间添加logo图片(图片合成)
     *
     * @param srcBitmap   原图片（生成的简单二维码图片）
     * @param logoBitmap  logo图片
     * @param logoPercent 百分比 (用于调整logo图片在原图片中的显示大小, 取值范围[0,1] )
     * @return
     */
    private static Bitmap addLogo(Bitmap srcBitmap, Bitmap logoBitmap, float logoPercent) {
        if (srcBitmap == null) {
            return null;
        }
        if (logoBitmap == null) {
            return srcBitmap;
        }
        //传值不合法时使用0.2F
        if (logoPercent < 0F || logoPercent > 1F) {
            logoPercent = 0.2F;
        }

        /** 1. 获取原图片和Logo图片各自的宽、高值 */
        int srcWidth = srcBitmap.getWidth();
        int srcHeight = srcBitmap.getHeight();
        int logoWidth = logoBitmap.getWidth();
        int logoHeight = logoBitmap.getHeight();

        /** 2. 计算画布缩放的宽高比 */
        float scaleWidth = srcWidth * logoPercent / logoWidth;
        float scaleHeight = srcHeight * logoPercent / logoHeight;

        /** 3. 使用Canvas绘制,合成图片 */
        Bitmap bitmap = Bitmap.createBitmap(srcWidth, srcHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawBitmap(srcBitmap, 0, 0, null);
        canvas.scale(scaleWidth, scaleHeight, srcWidth / 2, srcHeight / 2);
        canvas.drawBitmap(logoBitmap, srcWidth / 2 - logoWidth / 2, srcHeight / 2 - logoHeight / 2, null);
        return bitmap;
    }
}
