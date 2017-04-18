package com.baway.chilijie.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.baway.chilijie.bean.Classify;
import com.baway.chilijie.fragment.EffecFrament;
import com.baway.chilijie.fragment.SkinFrament;

/**
 * Created by MacBook- on 2017/4/15.
 */
public class EffecFragmentAdapter extends FragmentPagerAdapter {
    private FragmentManager fm;
    private Classify.Category category;
    public EffecFragmentAdapter(FragmentManager fm, Classify.Category category) {
        super(fm);
        this.category=category;
        this.fm=fm;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment=null;
        if (category.getCat_name().equals("按功效"))
       fragment= EffecFrament.getFragment(category.getChildren().get(position).getId());
        else if (category.getCat_name().equals("按肤质"))
            fragment= SkinFrament.getFragment(category.getChildren().get(position).getId());
        return fragment;
    }

    @Override
    public int getCount() {
        return category.getChildren().size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return category.getChildren().get(position).getCat_name();

    }
}
