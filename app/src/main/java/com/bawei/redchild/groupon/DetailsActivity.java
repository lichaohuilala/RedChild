package com.bawei.redchild.groupon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.bawei.redchild.R;

public class DetailsActivity extends AppCompatActivity {

    private Toolbar details_toobar;
    private WebView web_details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        initView();
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        web_details.setWebViewClient(new WebViewClient());
        WebSettings settings = web_details.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        web_details.loadUrl(url);
    }

    private void initView() {
        details_toobar = (Toolbar) findViewById(R.id.details_toobar);
        web_details = (WebView) findViewById(R.id.web_details);
        details_toobar.setNavigationIcon(R.mipmap.btn_back);
        details_toobar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
