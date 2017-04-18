package com.baway.chilijie.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baway.chilijie.R;
import com.baway.chilijie.activity.ClassifyEffectActivity;
import com.baway.chilijie.activity.ClassifyFaceActivity;
import com.baway.chilijie.activity.ClassifyMaskActivity;
import com.baway.chilijie.adapter.ClassifyStarGridviewAdapter;
import com.baway.chilijie.bean.Classify;
import com.baway.chilijie.view.MyImgTextTwo;
import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by MacBook- on 2017/4/12.
 */
public class ClassifyFragment extends Fragment implements View.OnClickListener {
    private String jsonurl="http://m.yunifang.com/yunifang/mobile/category/list?random=96333&encode=bf3386e14fe5bb0bcef234baebca2414";
    private View view;
    private ImageView classify_iv1,classify_iv2,classify_iv3,classify_iv4,classify_iv5,classify_iv6,
            classify_gongxiao_iv1,classify_gongxiao_iv2,classify_gongxiao_iv3,classify_gongxiao_iv4;
    private TextView classify_gongxiao,classify_fuzhi;
    private GridView classify_gv;
    private Classify classify;
    private Intent intentEffec;
    private Intent intentMask;
    private Intent intentFace;
    private EditText classify_et;
    private GridView classify_star_gv;
    private MyImgTextTwo textTwo;
    private Classify classify1;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==0){
                classify1=  (Classify) msg.obj;
                classify_gongxiao.setText(classify1.getCategory().get(0).getCat_name());
                classify_fuzhi.setText(classify1.getCategory().get(2).getCat_name());
                 adapter =  new ClassifyGridviewAdapter();
                classify_gv.setAdapter(adapter);
                classify_star_gv.setAdapter(new ClassifyStarGridviewAdapter(classify1.getGoodsBrief(),getActivity()));
            }
        }
    };
    private ImageView classify_gongxiao_iv5;
    private SwipeRefreshLayout srl;
    private LinearLayout layout;
    private ClassifyGridviewAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.classify_fragment,null);
        initView();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        intentEffec=new Intent(getActivity(), ClassifyEffectActivity.class);
        intentMask=new Intent(getActivity(), ClassifyMaskActivity.class);
        intentFace=new Intent(getActivity(), ClassifyFaceActivity.class);
        getServerData();
    }

    private void getServerData() {
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder().url(jsonurl).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                String json = response.body().string();
                try {
                    JSONObject data = new JSONObject(json).getJSONObject("data");
                    Gson gson=new Gson();
                    classify =   gson.fromJson(data.toString(), Classify.class);
                    Message.obtain(handler,0,classify).sendToTarget();
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });

    }

    private void initView() {
        classify_et = (EditText) view.findViewById(R.id.classify_et);
        classify_iv1 = (ImageView) view.findViewById(R.id.classify_iv1);
        classify_iv2 = (ImageView) view.findViewById(R.id.classify_iv2);
        classify_iv3 = (ImageView) view.findViewById(R.id.classify_iv3);
        classify_iv4 = (ImageView) view.findViewById(R.id.classify_iv4);
        classify_iv5 = (ImageView) view.findViewById(R.id.classify_iv5);
        classify_iv6 = (ImageView) view.findViewById(R.id.classify_iv6);
        classify_gongxiao = (TextView) view.findViewById(R.id.classify_gongxiao);
        classify_fuzhi = (TextView) view.findViewById(R.id.classify_fuzhi);
        classify_gongxiao_iv1 = (ImageView) view.findViewById(R.id.classify_gongxiao_iv1);
        classify_gongxiao_iv2 = (ImageView) view.findViewById(R.id.classify_gongxiao_iv2);
        classify_gongxiao_iv3 = (ImageView) view.findViewById(R.id.classify_gongxiao_iv3);
        classify_gongxiao_iv4 = (ImageView) view.findViewById(R.id.classify_gongxiao_iv4);
        classify_gongxiao_iv5 = (ImageView) view.findViewById(R.id.classify_gongxiao_iv5);
        srl = (SwipeRefreshLayout) view.findViewById(R.id.srl);
        classify_iv1.setOnClickListener(this);
        classify_iv2.setOnClickListener(this);
        classify_iv3.setOnClickListener(this);
        classify_iv4.setOnClickListener(this);
        classify_iv5.setOnClickListener(this);
        classify_iv6.setOnClickListener(this);
        classify_gongxiao_iv1.setOnClickListener(this);
        classify_gongxiao_iv2.setOnClickListener(this);
        classify_gongxiao_iv3.setOnClickListener(this);
        classify_gongxiao_iv4.setOnClickListener(this);
        classify_gongxiao_iv5.setOnClickListener(this);
        classify_gv = (GridView) view.findViewById(R.id.classify_gv);
        classify_star_gv = (GridView) view.findViewById(R.id.classify_star_gv);
        textTwo = (MyImgTextTwo) view.findViewById(R.id.mtt);
        textTwo.setIext("—  明显产品  —");
        textTwo.setIextSize(16);
        textTwo.setImage(R.mipmap.classify_triangle);
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
    private void toIntentEffec(int position) {
        intentEffec.putExtra("id",position);
        Bundle bundle=new Bundle();
        bundle.putSerializable("object",classify1.getCategory().get(2));
        intentEffec.putExtra("bundle",bundle);
        startActivityForResult(intentEffec,1);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.classify_iv1:
                Bundle bundle=new Bundle();
                bundle.putSerializable("object",classify1.getCategory().get(1));
                intentMask.putExtra("bundle",bundle);
                startActivityForResult(intentMask,100);
                break;
            case R.id.classify_iv2:
                intentFace.putExtra("id",classify1.getCategory().get(1).getChildren().get(1).getId());
                intentFace.putExtra("name",classify1.getCategory().get(1).getChildren().get(1).getCat_name());
                startActivityForResult(intentFace,101);
                break;
            case R.id.classify_iv3:
                intentFace.putExtra("id",classify1.getCategory().get(1).getChildren().get(2).getId());
                intentFace.putExtra("name",classify1.getCategory().get(1).getChildren().get(2).getCat_name());
                startActivityForResult(intentFace,101);
                break;
            case R.id.classify_iv4:
                intentFace.putExtra("id",classify1.getCategory().get(1).getChildren().get(3).getId());
                intentFace.putExtra("name",classify1.getCategory().get(1).getChildren().get(3).getCat_name());
                startActivityForResult(intentFace,101);
                break;
            case R.id.classify_iv5:
                intentFace.putExtra("id",classify1.getCategory().get(1).getChildren().get(4).getId());
                intentFace.putExtra("name",classify1.getCategory().get(1).getChildren().get(4).getCat_name());
                startActivityForResult(intentFace,101);
                break;
            case R.id.classify_iv6:
                intentFace.putExtra("id",classify1.getCategory().get(1).getChildren().get(5).getId());
                intentFace.putExtra("name",classify1.getCategory().get(1).getChildren().get(5).getCat_name());
                startActivityForResult(intentFace,101);
                break;
            case R.id.classify_gongxiao_iv1:
                intentEffec.putExtra("id",0);
                toIntent();
                break;
             case R.id.classify_gongxiao_iv2:
                 intentEffec.putExtra("id",1);
                 toIntent();
                 break;
             case R.id.classify_gongxiao_iv3:
                 intentEffec.putExtra("id",2);
                 toIntent();
                 break;
             case R.id.classify_gongxiao_iv4:
                 intentEffec.putExtra("id",3);
                 toIntent();
                 break;
            case R.id.classify_gongxiao_iv5:
                intentEffec.putExtra("id",4);
                toIntent();
                break;
        }
    }
    private void toIntent() {
        Bundle bundle=new Bundle();
        bundle.putSerializable("object",classify1.getCategory().get(0));
        intentEffec.putExtra("bundle",bundle);
        startActivityForResult(intentEffec,1);
    }
    class ClassifyGridviewAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return classify1.getCategory().get(2).getChildren().size();
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
        public View getView(final int position, View convertView, ViewGroup parent) {
            convertView=View.inflate(getActivity(), R.layout.classify_gv_item,null);
            layout = (LinearLayout) convertView.findViewById(R.id.classify_gv_item_ll);
            final TextView classify_gv_item_tv =  (TextView) convertView.findViewById(R.id.classify_gv_item_tv);
            switch (position){
                case 0:
                    layout.setBackgroundColor(getResources().getColor(R.color.colorClassifyGridviewText1));
                    break;
                case 1:
                    layout.setBackgroundColor(getResources().getColor(R.color.colorClassifyGridviewText2));
                    break;
                case 2:
                    layout.setBackgroundColor(getResources().getColor(R.color.colorClassifyGridviewText3));
                    break;
                case 3:
                    layout.setBackgroundColor(getResources().getColor(R.color.colorClassifyGridviewText4));
                    break;
                case 4:
                    layout.setBackgroundColor(getResources().getColor(R.color.colorClassifyGridviewText5));
                    break;
                case 5:
                    layout.setBackgroundColor(getResources().getColor(R.color.colorClassifyGridviewText6));
                    break;
            }
            classify_gv_item_tv.setText("#"+classify1.getCategory().get(2).getChildren().get(position).getCat_name()+"#");
            classify_gv_item_tv.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()){
                        case MotionEvent.ACTION_DOWN:
                            classify_gv_item_tv.setTextColor(Color.RED);
                            break;
                        case MotionEvent.ACTION_UP:
                            classify_gv_item_tv.setTextColor(Color.WHITE);
                            toIntentEffec(position);
                            break;
                    }
                    return true;
                }
            });
            return convertView;
        }
    }
}
