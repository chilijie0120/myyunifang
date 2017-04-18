package com.baway.chilijie.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.baway.chilijie.R;
import com.baway.chilijie.fragment.ClassifyFragment;
import com.baway.chilijie.fragment.HomePageFragment;
import com.baway.chilijie.fragment.MyFragment;
import com.baway.chilijie.fragment.ShoppingCarFragment;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private ViewPager vp;
    private RadioGroup rg;
    private RadioButton shouye;
    private RadioButton fenlei;
    private RadioButton gouwu;
    private RadioButton my;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();




    }

    private void initView() {
        vp = (ViewPager) findViewById(R.id.vp);
        rg = (RadioGroup) findViewById(R.id.rg);
        shouye = (RadioButton) findViewById(R.id.rb1);
        fenlei = (RadioButton) findViewById(R.id.rb2);
        gouwu = (RadioButton) findViewById(R.id.rb3);
        my = (RadioButton) findViewById(R.id.rb4);
        rg.setOnCheckedChangeListener(this);
        shouye.setChecked(true);
        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Fragment fragment=null;
                switch (position){
                    case 0:
                        fragment=new HomePageFragment();
                        break;
                    case 1:
                        fragment=new ClassifyFragment();
                        break;
                    case 2:
                        fragment=new ShoppingCarFragment();
                        break;
                    case 3:
                        fragment=new MyFragment();
                        break;
                }
                return fragment;
            }

            @Override
            public int getCount() {
                return 4;
            }
        });
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        shouye.setChecked(true);
                        break;
                    case 1:
                        fenlei.setChecked(true);
                        break;
                    case 2:
                        gouwu.setChecked(true);
                        break;
                    case 3:
                        my.setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.rb1:
                vp.setCurrentItem(0);
                break;
            case R.id.rb2:
                vp.setCurrentItem(1);
                break;
            case R.id.rb3:
                vp.setCurrentItem(2);
                break;
            case R.id.rb4:
                vp.setCurrentItem(3);
                break;
        }
    }
}
