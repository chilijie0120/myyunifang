package com.baway.chilijie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.baway.chilijie.R;
import com.baway.chilijie.adapter.EffecFragmentAdapter;
import com.baway.chilijie.bean.Classify;

public class ClassifyEffectActivity extends AppCompatActivity implements View.OnClickListener {

    private Classify.Category category;
    private TextView effec_title;
    private ImageView effec_back;
    private TabLayout effec_tab;
    private ViewPager effec_vp;


    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classify_effect);
        initView();
        id = getIntent().getIntExtra("id", 0);
        Bundle bundle = getIntent().getBundleExtra("bundle");
        category = (Classify.Category) bundle.getSerializable("object");
        effec_title.setText(category.getCat_name());
        effec_tab.setTabMode(TabLayout.MODE_FIXED);
        //effec_tab.addTab(effec_tab.newTab());
        effec_tab.setupWithViewPager(effec_vp);
        effec_vp.setAdapter(new EffecFragmentAdapter(getSupportFragmentManager(), category));
        effec_vp.setCurrentItem(id);

    }



    private void initView() {
        effec_title = (TextView) findViewById(R.id.effec_title);
        effec_back = (ImageView) findViewById(R.id.effec_back);
        effec_tab = (TabLayout) findViewById(R.id.effec_tab);
        effec_vp = (ViewPager) findViewById(R.id.effec_vp);
        effec_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(this,MainActivity.class);
        setResult(1,intent);
        finish();
    }
}
