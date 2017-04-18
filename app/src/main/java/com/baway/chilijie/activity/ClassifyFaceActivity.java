package com.baway.chilijie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

public class ClassifyFaceActivity extends AppCompatActivity {
    private RecyclerView rlv;
    private Button bt_retrun;
    private TextView tv_name;
    private List<EffecInfo> data;
    private String id;
    private String name;
    private MaskRecycleViewAdapter recycleViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classify_face);
        rlv= (RecyclerView) findViewById(R.id.rlv);
        bt_retrun= (Button) findViewById(R.id.bt_return);
        tv_name= (TextView) findViewById(R.id.tv_name);

        Intent it=getIntent();
        id =  it.getStringExtra("id");
        name = it.getStringExtra("name");
        //设置标题
        tv_name.setText(name);

        bt_retrun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent();
                setResult(101,it);
                finish();
            }
        });
        //获取数据
        getServerData();
    }
    private void getServerData() {
        String url="http://m.yunifang.com/yunifang/mobile/goods/getall?random=91873&encode=68301f6ea0d6adcc0fee63bd21815d69&category_id="+id;
        RequestQueue queue = Volley.newRequestQueue(ClassifyFaceActivity.this);
        StringRequest request=new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                JSONArray jsonArray = null;
                try {
                    jsonArray = new JSONObject(s).optJSONArray("data");

                Gson gson=new Gson();
                Type type=new TypeToken<List<EffecInfo>>(){}.getType();
                data = gson.fromJson(jsonArray.toString(), type);
                //设置布局管理器
                GridLayoutManager gridLayoutManager=new GridLayoutManager(ClassifyFaceActivity.this,2);
                rlv.setLayoutManager(gridLayoutManager);
                //设置适配器
                recycleViewAdapter= new MaskRecycleViewAdapter(ClassifyFaceActivity.this,data);
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
