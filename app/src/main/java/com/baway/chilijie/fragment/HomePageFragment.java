package com.baway.chilijie.fragment;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.baway.chilijie.R;
import com.baway.chilijie.activity.HomeXbannerWebActivity;
import com.baway.chilijie.adapter.HomeGridviewAdapter;
import com.baway.chilijie.adapter.HomeListviewAdapter;
import com.baway.chilijie.adapter.MyViewpagerAdapter;
import com.baway.chilijie.adapter.MyXBannerAdapter;
import com.baway.chilijie.bean.Data;
import com.baway.chilijie.other.MyGallyPageTransformer;
import com.baway.chilijie.view.MyImgText;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.stx.xhb.xbanner.XBanner;

import org.json.JSONObject;

import java.util.List;

import chilijie.baway.com.gotopscrollviewlibrary.GoTopScrollview;

/**
 * Created by MacBook- on 2017/4/11.
 */
public class HomePageFragment extends Fragment implements XBanner.OnItemClickListener, View.OnClickListener {

    private View view;
    private MyImgText mit1;
    private MyImgText mit2;
    private MyImgText mit3;
    private MyImgText mit4;
    private String jsonUrl="http://m.yunifang.com/yunifang/mobile/home?random=84831&encode=9dd34239798e8cb22bf99a75d1882447";
    private GoTopScrollview gst;
    private ImageView iv_totop;
    private XBanner xb;
    private LinearLayout ll;
    private TextView home_tv2;
    private ListView lv;
    private GridView gv;
    private ViewPager vp;
    private SwipeRefreshLayout srl;
    private Handler handler=new Handler();
    private Data data;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.homepage_fragment,null);
        initView();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getServerData();
        gst.setHeight(400);
        gst.setImgeViewOnClickListener(iv_totop);


    }

    private void getServerData() {
        RequestQueue queue= Volley.newRequestQueue(getActivity());
        StringRequest request=new StringRequest(jsonUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                try {
                    JSONObject json = new JSONObject(s).getJSONObject("data");
                    Gson gson=new Gson();
                     data =  gson.fromJson(json.toString(), Data.class);
                    xb.setData(data.getAd1(),null);
                    xb.setmAdapter(new MyXBannerAdapter(data.getAd1(),getActivity()));
                    setData(data.getAd5());
                    setSpringHotData(data.getBestSellers());

                    //setOffscreenPageLimit表示设置缓存，这样左右拖动即可看见后面的图片
                    vp.setOffscreenPageLimit(3);
                    int pagerWidth = (int) (getResources().getDisplayMetrics().widthPixels * 3.0f / 5.0f);
                    ViewGroup.LayoutParams lp = vp.getLayoutParams();
                    if (lp == null) {
                        lp = new ViewGroup.LayoutParams(pagerWidth, ViewGroup.LayoutParams.MATCH_PARENT);
                    } else {
                        lp.width = pagerWidth;
                    }
                    vp.setLayoutParams(lp);
                    //setPageMargin表示设置图片之间的间距
                    vp.setPageMargin(getResources().getDimensionPixelSize(R.dimen.activity_horizontal_margin));
                    vp.setPageTransformer(true,new MyGallyPageTransformer());
                    vp.setAdapter(new MyViewpagerAdapter(data.getActivityInfo().getActivityInfoList(),getActivity()));
                    vp.setCurrentItem(2000);

                    lv.setAdapter(new HomeListviewAdapter(data.getSubjects(),getActivity()));
                    setListviewHeight();
                    gv.setAdapter(new HomeGridviewAdapter(data.getDefaultGoodsList(),getActivity()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        queue.add(request);
    }

    private void setListviewHeight() {
        ListAdapter adapter = lv.getAdapter();
        int height=0;
        for (int i = 0; i <adapter.getCount() ; i++) {
            View adapterView = adapter.getView(i, null, lv);
            adapterView.measure(0,0);
            height+= adapterView.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = lv.getLayoutParams();
        params.height=height+(lv.getDividerHeight()*(adapter.getCount()-1));
        lv.setLayoutParams(params);
    }


    private void setSpringHotData(List<Data.BestSellers> bestSellers) {


        home_tv2.setText(bestSellers.get(0).getName());
        for (int i = 0; i <bestSellers.get(0).getGoodsList().size() ; i++) {
            View view1=View.inflate(getActivity(),R.layout.linerlayout_item,null);
            ImageView ll_iv= (ImageView) view1.findViewById(R.id.ll_iv);
            TextView ll_tv1= (TextView) view1.findViewById(R.id.ll_tv1);
            TextView ll_tv2= (TextView) view1.findViewById(R.id.ll_tv2);
            TextView ll_tv3= (TextView) view1.findViewById(R.id.ll_tv3);

            Glide.with(getActivity()).load(bestSellers.get(0).getGoodsList().get(i).getGoods_img()).into(ll_iv);
            ll_tv1.setText(bestSellers.get(0).getGoodsList().get(i).getGoods_name());
            ll_tv2.setText("￥"+bestSellers.get(0).getGoodsList().get(i).getShop_price());
            ll_tv3.setText("￥"+bestSellers.get(0).getGoodsList().get(i).getMarket_price());
            ll_tv3.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            ll.addView(view1);
        }

    }

    private void setData(List<Data.Ad5Info> ad5) {
        mit1.setImage(ad5.get(0).getImage());
        mit2.setImage(ad5.get(1).getImage());
        mit3.setImage(ad5.get(2).getImage());
        mit4.setImage(ad5.get(3).getImage());

        mit1.setIext(ad5.get(0).getTitle());
        mit2.setIext(ad5.get(1).getTitle());
        mit3.setIext(ad5.get(2).getTitle());
        mit4.setIext(ad5.get(3).getTitle());

        mit1.setIextSize(13);
        mit2.setIextSize(13);
        mit3.setIextSize(13);
        mit4.setIextSize(13);
    }


    private void initView() {
        gst = (GoTopScrollview) view.findViewById(R.id.gts);
        iv_totop = (ImageView) view.findViewById(R.id.iv_totop);
        xb = (XBanner) view.findViewById(R.id.xb);
        vp = (ViewPager) view.findViewById(R.id.vp);

        srl = (SwipeRefreshLayout) view.findViewById(R.id.srl);
        ll = (LinearLayout) view.findViewById(R.id.ll);
        home_tv2 = (TextView) view.findViewById(R.id.home_tv2);
        lv = (ListView) view.findViewById(R.id.home_lv);
        gv = (GridView) view.findViewById(R.id.home_gv);

        mit1 = (MyImgText) view.findViewById(R.id.mit1);
        mit2 = (MyImgText) view.findViewById(R.id.mit2);
        mit3 = (MyImgText) view.findViewById(R.id.mit3);
        mit4 = (MyImgText) view.findViewById(R.id.mit4);
        xb.setOnItemClickListener(this);
        mit1.setOnClickListener(this);
        mit2.setOnClickListener(this);
        mit3.setOnClickListener(this);
        mit4.setOnClickListener(this);

        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getServerData();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        srl.setRefreshing(false);
                    }
                },2000);
            }
        });
    }

    @Override
    public void onItemClick(XBanner banner, int position) {
        Intent intent=new Intent(getActivity(), HomeXbannerWebActivity.class);
        intent.putExtra("url",data.getAd1().get(position).getAd_type_dynamic_data());
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {

    }
}
