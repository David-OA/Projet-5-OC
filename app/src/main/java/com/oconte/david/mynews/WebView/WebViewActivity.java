package com.oconte.david.mynews.WebView;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.oconte.david.mynews.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebViewActivity extends AppCompatActivity {

    // For Design
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.web_view_all_new) WebView webView;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);

        this.configureToolbar();
        this.configureWebView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    protected void configureToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("My News");

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    /**
     * It's for manage the webView after click on one article for see the details
     * and save that it has been read.
     */
    protected void configureWebView() {
        WebView mWebView = (WebView) findViewById(R.id.web_view_all_new);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                findViewById(R.id.web_view_all_new).setVisibility(View.VISIBLE);
            }
        });
        mWebView.loadUrl(getUrl());

        SharedPreferences preferencesUrl = getSharedPreferences("UrlPrefs", MODE_PRIVATE);
        String urls = preferencesUrl.getString("EXTRA_URL", "");

            preferencesUrl
                    .edit()
                    .putString("EXTRA_URL", urls + "," + getUrl())
                    .apply();

    }

    public String getUrl() {
        return getIntent().getStringExtra("url");
    }
}
