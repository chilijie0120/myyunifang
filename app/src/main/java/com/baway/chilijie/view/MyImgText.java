package com.baway.chilijie.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baway.chilijie.R;
import com.bumptech.glide.Glide;

/**
 * Created by MacBook- on 2017/4/12.
 */
public class MyImgText extends LinearLayout {
    private ImageView iv;
    private TextView tv;
    private Context context;
    public MyImgText(Context context) {
        super(context);
        this.context=context;
        initView();
    }

    public MyImgText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        initView();
    }

    public MyImgText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
        initView();
    }
    private void initView() {
        inflate(getContext(), R.layout.custom_myimgtext, this);
        tv= (TextView) findViewById(R.id.myimgtext_tv);
        iv= (ImageView) findViewById(R.id.myimgtext_iv);
        //addView(view);
    }
    public void setImage(String url){
        Glide.with(context).load(url).into(iv);
    }
    public void setIext(String str){
        tv.setText(str);
    }
    public void setIextSize(float size){
        tv.setTextSize(size);
    }
    public void setIextColor(int color){
        tv.setTextColor(color);

    }
}
