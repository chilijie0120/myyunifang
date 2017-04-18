package com.baway.chilijie.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.baway.chilijie.bean.Data;
import com.bumptech.glide.Glide;
import com.stx.xhb.xbanner.XBanner;

import java.util.List;

/**
 * Created by MacBook- on 2017/4/11.
 */
public class MyXBannerAdapter implements XBanner.XBannerAdapter{
    private List<Data.Ad1Info> ad1Infos;
    private Context context;

    public MyXBannerAdapter(List<Data.Ad1Info> ad1Infos, Context context) {
        this.ad1Infos = ad1Infos;
        this.context = context;
    }

    @Override
    public void loadBanner(XBanner banner, View view, int position) {
        Glide.with(context).load(ad1Infos.get(position).getImage()).into((ImageView) view);
    }
}
