package com.baway.chilijie.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.baway.chilijie.R;
import com.baway.chilijie.adapter.EffecGridviewAdapter;
import com.baway.chilijie.bean.EffecInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by MacBook- on 2017/4/15.
 */
public class SkinFrament extends Fragment {
    private String jsonUrl;
    private List<EffecInfo> effecInfos;
    private String id;
    private GridView effec_gv;

    public static SkinFrament getFragment(String id){
        SkinFrament frament=new SkinFrament();
        Bundle bundle=new Bundle();
        bundle.putString("id",id);
        frament.setArguments(bundle);
        return frament;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.effecfragment,null);

        effec_gv = (GridView) view.findViewById(R.id.effec_gv);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        id = getArguments().getString("id");
        getServerData(id);
    }
    private void getServerData(String id) {
        jsonUrl="http://m.yunifang.com/yunifang/mobile/goods/getall?" +
                "random=86588&encode=102e81c24a35dbdc9bb130c3c164434b&category_id="+id;
        AsyncHttpClient client=new AsyncHttpClient();
        client.get(jsonUrl, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                try {
                    JSONArray jsonArray = new JSONObject(responseString).optJSONArray("data");
                    Gson gson=new Gson();
                    Type type=new TypeToken<List<EffecInfo>>(){}.getType();
                    effecInfos= gson.fromJson(jsonArray.toString(), type);
                    effec_gv.setAdapter(new EffecGridviewAdapter(effecInfos,getActivity()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
