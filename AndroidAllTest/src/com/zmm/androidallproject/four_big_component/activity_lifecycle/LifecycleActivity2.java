package com.zmm.androidallproject.four_big_component.activity_lifecycle;

import com.zmm.androidallproject.mainUI.AllApplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

public class LifecycleActivity2 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		System.out.println("LifecycleActivity2.onCreate()");

		String msg = getIntent().getStringExtra("msg");

		if (msg != null && msg.equals("startActivityForResult")) {
			AllApplication.allDebug(this,
					"receive command startActivityForResult");
			Intent data = new Intent();
			data.putExtra("msg", "this msg is from second Activity");
			setResult(888, data);
			finish();
		}

	}

	@Override
	public View onCreateView(String name, Context context, AttributeSet attrs) {
		// System.out.println("LifecycleActivity2.onCreateView()1");
		return super.onCreateView(name, context, attrs);
	}

	@SuppressLint("NewApi")
	@Override
	public View onCreateView(View parent, String name, Context context,
			AttributeSet attrs) {
		// System.out.println("LifecycleActivity2.onCreateView()2");
		return super.onCreateView(parent, name, context, attrs);
	}

	@Override
	public void onBackPressed() {
		AllApplication.allDebug(this, "onBackPressed");
		super.onBackPressed();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		System.out.println("LifecycleActivity2.onDestroy()");
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		System.out.println("LifecycleActivity2.onNewIntent()");
	}

	@Override
	protected void onPause() {
		super.onPause();
		System.out.println("LifecycleActivity2.onPause()");
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		System.out.println("LifecycleActivity2.onRestart()");
	}

	@Override
	protected void onResume() {
		super.onResume();
		System.out.println("LifecycleActivity2.onResume()");
	}

	@Override
	protected void onStart() {
		super.onStart();
		System.out.println("LifecycleActivity2.onStart()");
	}

	@Override
	protected void onStop() {
		super.onStop();
		System.out.println("LifecycleActivity2.onStop()");
	}

}
