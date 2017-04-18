package com.baway.chilijie.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.baway.chilijie.R;

public class HomeXbannerWebActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_xbanner_web);
        WebView wv= (WebView) findViewById(R.id.homeWv);
        String url = getIntent().getStringExtra("url");
        wv.getSettings().setJavaScriptEnabled(true);

        wv.loadUrl(url);
    }
}
