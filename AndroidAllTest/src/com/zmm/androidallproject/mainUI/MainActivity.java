package com.zmm.androidallproject.mainUI;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.Toast;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.zmm.androidallproject.R;
import com.zmm.androidallproject.four_big_component.ViewpagerIndicatorMainFragment;
import com.zmm.androidallproject.four_big_component.activity_lifecycle.PendingBroadcastReceiver;
import com.zmm.androidallproject.four_big_component.service.AllService;

public class MainActivity extends ActivityForConsole implements MainMenu.OnItemOneClickListener{

	private SlidingMenu slideMenu;
	Fragment mainMenu ;
	Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getWindow().requestFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.main);
		System.out.println("thread id : "+Thread.currentThread().getId());
		context = getApplicationContext();

		// initialize slideingMenu
		slideMenu = new SlidingMenu(this);
		slideMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
		slideMenu.setShadowWidthRes(R.dimen.shadow_width);
		slideMenu.setShadowDrawable(R.drawable.shadow);
		slideMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);

		slideMenu.setFadeDegree(0.35f);
		slideMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
		slideMenu.setMenu(R.layout.menu_frame);

		mainMenu = new MainMenu();
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.menu_frame, mainMenu).commit();

		getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.mainContainer,
						new ViewpagerIndicatorMainFragment()).commit();

		if (savedInstanceState != null) {
			if (savedInstanceState.getString("msgState") != null) {
				String msg = savedInstanceState.getString("msgState");
				System.out.println(msg + " from onCreate()");
			}
		}
		
		readMetadata();
	}
	
	/**
	 * the metadata info is corresponding the ComponentName,
	 */
	public void readMetadata(){
		try {
			//get application metadata info
			ApplicationInfo appInfo = this.getPackageManager()
					.getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
			
//			String info1 = appInfo.metaData.getString("adverts_type");
			int info1 = appInfo.metaData.getInt("adverts_type");
//			Toast.makeText(context, info1+"", 0).show();
			
			//in activity ,the meta data info must in the current activity??
			ActivityInfo activityInfo = this.getPackageManager()
					.getActivityInfo(getComponentName(), PackageManager.GET_META_DATA);
			String inforActivity = activityInfo.metaData.getString("activity_info");
//			Toast.makeText(context, inforActivity, 0).show();
			
			//in service
			ComponentName cn = new ComponentName(context, AllService.class);
			ServiceInfo serviceInfo = this.getPackageManager()
					.getServiceInfo(cn, PackageManager.GET_META_DATA);
			String infoService = serviceInfo.metaData.getString("service_meta");
			Toast.makeText(context, infoService, 0).show();
//			Log.d("moon", cn.toString());
			
			ComponentName cnReceiver = new ComponentName(context, PendingBroadcastReceiver.class);
			ActivityInfo receiverInfo = this.getPackageManager()
					.getReceiverInfo(cnReceiver, PackageManager.GET_META_DATA);
			Log.d("moon", receiverInfo.metaData.getString("receiver_meta"));
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(context, "catch()...", 1).show();
		}
		
	}
	
	public void toggle(){
		slideMenu.toggle();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putString("msgState", "this is a msg from onSaveInstanceState");
	}

	// @Override
	// protected void onRestart() {
	// super.onRestart();
	// if(getIntent().getb != null){
	// String msg = getIntent().getStringExtra("msgState");
	// if(msg != null){
	// System.out.println(msg+" from onRestart()");
	// }
	// }
	// }

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		if (savedInstanceState != null) {
			if (savedInstanceState.getString("msgState") != null) {
				String msg = savedInstanceState.getString("msgState");
				System.out.println(msg + " from onRestoreInstanceState()");
			}
		}
	}

	// menu点击事件
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_MENU) {
			AllApplication.allDebug(MainActivity.this, "onMenuKeyClick");
			slideMenu.toggle();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	// @Override
	// public boolean onCreateOptionsMenu(Menu menu) {
	// // Inflate the menu; this adds items to the action bar if it is present.
	// getMenuInflater().inflate(R.menu.main, menu);
	// return true;
	// }

	// @Override
	// public boolean onOptionsItemSelected(MenuItem item) {
	// switch (item.getItemId()) {
	// case R.id.menu_add:
	// Toast.makeText(getApplicationContext(), "add",
	// Toast.LENGTH_SHORT).show();
	// break;
	//
	// default:
	// break;
	// }
	// return super.onOptionsItemSelected(item);
	// }

	// @Override
	// public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu)
	// {
	// getSupportMenuInflater().inflate(R.menu.sherlock_menu, menu);
	// return super.onCreateOptionsMenu(menu);
	// }

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onItemOneClick() {
		slideMenu.toggle();
	}

}
