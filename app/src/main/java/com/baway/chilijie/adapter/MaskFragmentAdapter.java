package com.baway.chilijie.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.baway.chilijie.bean.Classify;
import com.baway.chilijie.fragment.MaskFragment;

import java.util.List;

/**
 * Created by MacBook- on 2017/4/15.
 */
public class MaskFragmentAdapter extends FragmentPagerAdapter {
    private FragmentManager fm;
    private List<Classify.Category.Children> list;
    public MaskFragmentAdapter(FragmentManager fm, List<Classify.Category.Children> list) {
        super(fm);
        this.list=list;
        this.fm=fm;
    }

    @Override
    public Fragment getItem(int position) {
        return MaskFragment.getInster(list.get(position).getId());
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position).getCat_name();
    }

}
