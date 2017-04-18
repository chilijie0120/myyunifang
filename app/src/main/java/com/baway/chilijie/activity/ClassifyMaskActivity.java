package com.baway.chilijie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.baway.chilijie.R;
import com.baway.chilijie.adapter.MaskFragmentAdapter;
import com.baway.chilijie.bean.Classify;

import java.util.ArrayList;
import java.util.List;

public class ClassifyMaskActivity extends AppCompatActivity {
    private TabLayout tab;
    private ViewPager vp;
    private List<Classify.Category.Children> list=new ArrayList<>();
    private Button bt_return;
    private Classify.Category category;
    private TextView mask_title;
    private List<Classify.Category.Children> children;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classify_mask);
        tab= (TabLayout) findViewById(R.id.tab);
        bt_return= (Button) findViewById(R.id.bt_return);
        vp= (ViewPager) findViewById(R.id.vp);
        mask_title = (TextView) findViewById(R.id.mask_title);
        Bundle bundle = getIntent().getBundleExtra("bundle");
        category = (Classify.Category) bundle.getSerializable("object");
        mask_title.setText(category.getChildren().get(0).getCat_name());
        bt_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent();
                setResult(100,it);
                finish();
            }
        });
        children = category.getChildren();
        for (int i = 0; i < children.size(); i++) {
            if (i==6||i==7||i==8)
            list.add(children.get(i));
        }
        //把数据添加到list集合中

        //list.add("泥浆面膜");
        //设置适配器
        MaskFragmentAdapter adapter=new MaskFragmentAdapter(getSupportFragmentManager(),list);
        vp.setAdapter(adapter);
        //设置模式  静止
        tab.setTabMode(TabLayout.MODE_FIXED);
        //让tablayout与viewpager关联
        tab.setupWithViewPager(vp);
    }
}
