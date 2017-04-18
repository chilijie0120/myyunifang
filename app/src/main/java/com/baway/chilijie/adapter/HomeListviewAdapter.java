package com.baway.chilijie.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baway.chilijie.R;
import com.baway.chilijie.bean.Data;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by MacBook- on 2017/4/13.
 */
public class HomeListviewAdapter extends BaseAdapter {
    private List<Data.Subjects> subjects;
    private Context context;
    public HomeListviewAdapter(List<Data.Subjects> subjects, Context context) {
      this.subjects=subjects;
        this.context=context;
    }

    @Override
    public int getCount() {
        return subjects.size()!=0?subjects.size():0;
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
        convertView=View.inflate(context, R.layout.home_listview_item,null);
        ImageView lv_item_iv= (ImageView) convertView.findViewById(R.id.home_listview_item_iv);
        LinearLayout lv_item_ll= (LinearLayout) convertView.findViewById(R.id.home_listview_item_ll);
        Glide.with(context).load(subjects.get(position).getImage()).into(lv_item_iv);
        for (int i = 0; i <subjects.get(position).getGoodsList().size() ; i++) {
            View view1=View.inflate(context,R.layout.linerlayout_item,null);
            ImageView ll_iv= (ImageView) view1.findViewById(R.id.ll_iv);
            TextView ll_tv1= (TextView) view1.findViewById(R.id.ll_tv1);
            TextView ll_tv2= (TextView) view1.findViewById(R.id.ll_tv2);
            TextView ll_tv3= (TextView) view1.findViewById(R.id.ll_tv3);

            Glide.with(context).load(subjects.get(position).getGoodsList().get(i).getGoods_img()).into(ll_iv);
            ll_tv1.setText(subjects.get(position).getGoodsList().get(i).getGoods_name());
            ll_tv2.setText("￥"+subjects.get(position).getGoodsList().get(i).getShop_price());
            ll_tv3.setText("￥"+subjects.get(position).getGoodsList().get(i).getMarket_price());
            ll_tv3.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            lv_item_ll.addView(view1);
        }


        return convertView;
    }
}
