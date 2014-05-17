package com.zmm.androidallproject.four_big_component.activity_lifecycle;

import com.zmm.androidallproject.R;

import android.app.Activity;
import android.content.pm.FeatureInfo;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewActivity extends Activity {
	WebView webView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.webview_layout);
		webView = (WebView) findViewById(R.id.webView1);
		initWebview();
		
		webView.loadUrl("http://www.baidu.com");
		webView.loadUrl("http://www.qq.com");
		
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()){
			webView.goBack();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	
	public void initWebview(){
		WebSettings webSettings = webView.getSettings();
		webSettings.setJavaScriptEnabled(true);
		
		webView.setWebChromeClient(new WebChromeClient(){
			//????
		});
		
		webView.setWebViewClient(new WebViewClient(){
			//如果页面中链接，并且不用Android自带的browser处理的话
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			};
			
		}
		);
		
		webView.addJavascriptInterface(new Object(){
			
		}, "demo");
	}
	
}
