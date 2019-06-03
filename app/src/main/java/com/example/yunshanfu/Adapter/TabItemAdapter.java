package com.example.yunshanfu.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yunshanfu.Model.Item;
import com.example.yunshanfu.R;

import java.util.List;

/**
 * 使用ViewHolder和Adapter来做recyclerView的适配
 * tab页的adapter
 */
public class TabItemAdapter extends RecyclerView.Adapter<TabItemAdapter.ViewHolder> {

    private List<Item> mItemlist;

    public TabItemAdapter(List<Item> mItemlist) {
        this.mItemlist = mItemlist;
    }

    @NonNull
    @Override
    public TabItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // 获取单个item的实例，构造ViewHolder,ViewHolder在RecyclerView显示
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.grid_layout_item_tab, viewGroup,
                false);
        final ViewHolder holder = new ViewHolder(view);
        // 为每一个组件的点击添加监听事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Item item = mItemlist.get(position);
                Toast.makeText(v.getContext(), "you clicked view " + item.getDescription(), Toast.LENGTH_SHORT).show();
            }
        });
        holder.itemImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Item item = mItemlist.get(position);
                Toast.makeText(v.getContext(), "you clicked view " + item.getDescription(), Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TabItemAdapter.ViewHolder viewHolder, int i) {
        Item item = mItemlist.get(i);
        // 设置图片为圆角，可以抽象成一个方法

        viewHolder.itemImage.setImageResource(item.getImageId());
        viewHolder.itemDescription.setText(item.getDescription());
    }

    @Override
    public int getItemCount() {
        return mItemlist.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView itemImage;
        TextView itemDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.item_image);
            itemDescription = itemView.findViewById(R.id.item_description);
        }
    }
}

