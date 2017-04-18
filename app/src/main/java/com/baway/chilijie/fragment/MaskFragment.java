package com.baway.chilijie.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.baway.chilijie.R;
import com.baway.chilijie.adapter.MaskRecycleViewAdapter;
import com.baway.chilijie.bean.EffecInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by MacBook- on 2017/4/17.
 */
public class MaskFragment extends Fragment{
    private List<EffecInfo> data;
    private MaskRecycleViewAdapter recycleViewAdapter;
    private RecyclerView rlv;
    private String id;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.maskfragment, null);
        rlv = (RecyclerView) view.findViewById(R.id.rlv);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //获取数据
        getServerData();
    }
    public static Fragment getInster(String id){
        MaskFragment contentFragment=new MaskFragment();
        Bundle bundle=new Bundle();
        bundle.putString("id",id);
        contentFragment.setArguments(bundle);
        return contentFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = getArguments().getString("id");
    }

    private void getServerData() {
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        String url="http://m.yunifang.com/yunifang/mobile/goods/getall?random=91873&encode=68301f6ea0d6adcc0fee63bd21815d69&category_id="+id;

        StringRequest request=new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                try {
                    JSONArray jsonArray = new JSONObject(s).optJSONArray("data");
                Gson gson=new Gson();
                Type type=new TypeToken<List<EffecInfo>>(){}.getType();
                data = gson.fromJson(jsonArray.toString(), type);
                //设置布局管理器
                GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity(),2);
                rlv.setLayoutManager(gridLayoutManager);
                //设置适配器
                recycleViewAdapter= new MaskRecycleViewAdapter(getActivity(),data);
                rlv.setAdapter(recycleViewAdapter);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //对条目进行监听
                recycleViewAdapter.setItemClickListener(new MaskRecycleViewAdapter.OnItemClickListener() {
                    @Override
                    public void onItenClick(View view, int position) {

                    }
                });
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
            }
        });
        queue.add(request);
    }
}
