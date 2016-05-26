package com.wondertwo.imageloader.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.wondertwo.imageloader.R;
import com.wondertwo.imageloader.utils.AsyncImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * StaggeredAdapter 瀑布流式的RecyclerView数据源适配器
 *
 * Created by wondertwo on 2016/5/19.
 */
public class StaggeredAdapter extends RecyclerView.Adapter<StaggeredAdapter.MyViewHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    private String[] mDatas;
    private AsyncImageLoader mImageLoader;
    private List<Integer> mHeights;

    public StaggeredAdapter(Context context, String[] data) {
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
        mDatas = data;
        mImageLoader = new AsyncImageLoader(mContext);

        // 随机生成item的高度
        mHeights = new ArrayList<>();
        for (String mData : mDatas) {
            mHeights.add((int) (100 + Math.random() * 300));
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(mInflater.inflate(R.layout.recyclerview_item, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // 设置item高度
        ViewGroup.LayoutParams lp = holder.iv.getLayoutParams();
        lp.height = mHeights.get(position);
        holder.iv.setLayoutParams(lp);

        final String imgUrl = mDatas[position];
        // 给 ImageView 设置一个 tag
        holder.iv.setTag(imgUrl);
        // 给 ImageView 预设一个图片
        holder.iv.setImageResource(R.drawable.ic_launcher);

        if (!TextUtils.isEmpty(imgUrl)) {
            Bitmap bitmap = mImageLoader.loadImage(holder.iv, imgUrl);
            if (bitmap != null) {
                holder.iv.setImageBitmap(bitmap);
            }
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.length;
    }

    // ViewHolder
    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        public MyViewHolder(View view) {
            super(view);
            iv = (ImageView) view.findViewById(R.id.surface_user_image);
        }
    }
}
