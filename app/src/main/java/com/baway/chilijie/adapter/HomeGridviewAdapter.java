package com.baway.chilijie.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.baway.chilijie.R;
import com.baway.chilijie.bean.Data;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by MacBook- on 2017/4/13.
 */
public class HomeGridviewAdapter extends BaseAdapter {
    private List<Data.DefaultGoodsList> defaultGoodsLists;
    private Context context;
    public HomeGridviewAdapter(List<Data.DefaultGoodsList> defaultGoodsLists, Context context) {
      this.defaultGoodsLists=defaultGoodsLists;
        this.context=context;
    }

    @Override
    public int getCount() {
        return defaultGoodsLists.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView=View.inflate(context, R.layout.linerlayout_gridview_item,null);
            ImageView ll_iv= (ImageView) convertView.findViewById(R.id.ll_iv);
            TextView ll_tv0= (TextView) convertView.findViewById(R.id.ll_tv0);
            TextView ll_tv1= (TextView) convertView.findViewById(R.id.ll_tv1);
            TextView ll_tv2= (TextView) convertView.findViewById(R.id.ll_tv2);
            TextView ll_tv3= (TextView) convertView.findViewById(R.id.ll_tv3);

            Glide.with(context).load(defaultGoodsLists.get(position).getGoods_img()).into(ll_iv);
            ll_tv0.setText(defaultGoodsLists.get(position).getEfficacy());
            ll_tv1.setText(defaultGoodsLists.get(position).getGoods_name());
            ll_tv2.setText("￥"+defaultGoodsLists.get(position).getShop_price());
            ll_tv3.setText("￥"+defaultGoodsLists.get(position).getMarket_price());
            ll_tv3.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);



        return convertView;
    }
}
