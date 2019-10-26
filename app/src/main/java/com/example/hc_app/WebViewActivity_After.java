package com.example.hc_app;

import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class WebViewActivity_After extends AppCompatActivity{

    private WebView myWebView;
    private WebViewClient myWebViewClient;

    private boolean formResponseFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.hide();
        }

        myWebView = findViewById(R.id.webview);
        myWebViewClient = new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                /*
                if (url.contains("blog.csdn.net")){
                    view.loadUrl("https://www.google.com");
                }
                */
                // finish();
                return false;
            }
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
                if ((url.startsWith("http") || url.startsWith("https")) && url.contains("formResponse")) {
                    formResponseFlag = true;    // for future use...
                    finish();
                }
                return super.shouldInterceptRequest(view, url);
            }
        };

        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.setWebViewClient(myWebViewClient);
        myWebView.loadUrl("https://docs.google.com/forms/d/e/1FAIpQLSdkL2Muv3f1Uae3elda9t5bKpGGgTNlC3YPMnEPuTlmyNnpGg/viewform?usp=sf_link");
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && myWebView.canGoBack()){
            myWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
