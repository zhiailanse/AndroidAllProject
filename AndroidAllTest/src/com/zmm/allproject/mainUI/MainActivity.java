package com.zmm.allproject.mainUI;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.zmm.allproject.R;
import com.zmm.allproject.four_big_component.ViewpagerIndicatorMainFragment;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;

public class MainActivity extends ActivityForConsole {

	private SlidingMenu slideMenu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getWindow().requestFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.main);

		// start service
		// Intent serviceIntent = new Intent(AllService.AllServiceAction);
		// startService(serviceIntent);

		// initialize slideingMenu
		slideMenu = new SlidingMenu(this);
		slideMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
		slideMenu.setShadowWidthRes(R.dimen.shadow_width);
		slideMenu.setShadowDrawable(R.drawable.shadow);
		slideMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);

		slideMenu.setFadeDegree(0.35f);
		slideMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
		slideMenu.setMenu(R.layout.menu_frame);

		getSupportFragmentManager().beginTransaction()
				.replace(R.id.menu_frame, new MainMenu()).commit();

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
		// Intent serviceIntent = new Intent(AllService.AllServiceAction);
		// stopService(serviceIntent);
		super.onDestroy();
	}

}
