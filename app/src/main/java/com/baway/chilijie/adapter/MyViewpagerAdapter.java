package com.baway.chilijie.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.baway.chilijie.bean.Data;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by MacBook- on 2017/4/13.
 */
public class MyViewpagerAdapter extends PagerAdapter {
    private List<Data.MyInfo.MyInfoList> myInfoLists;
    private Context context;
    private int xinWidth;
    private int xinHeight;

    public MyViewpagerAdapter(List<Data.MyInfo.MyInfoList> myInfoLists, Context context) {
       this.context=context;
        this.myInfoLists=myInfoLists;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView vp_iv= new ImageView(context);
        Glide.with(context).load(myInfoLists.get(position%myInfoLists.size()).getActivityImg()).into(vp_iv);
        container.addView(vp_iv);
        return vp_iv;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
      container.removeView((View) object);
    }
}
