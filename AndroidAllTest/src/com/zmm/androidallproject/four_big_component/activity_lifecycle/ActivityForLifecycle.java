package com.zmm.androidallproject.four_big_component.activity_lifecycle;

import com.zmm.androidallproject.R;
import com.zmm.androidallproject.mainUI.ActivityForConsole;
import com.zmm.androidallproject.mainUI.AllApplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ActivityForLifecycle extends ActivityForConsole {

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onAttachFragment(android.app.Fragment) Activity
	 * lifecycle begin: onCreate onStart onResume
	 * 
	 * while jump to another Activity : onPause onSaveInstanceState onStop
	 * 
	 * while back to the orignal Activity : onRestart onStart onResume
	 * 
	 * while backPress : onPause onStop onDestroy
	 * 
	 * while shutDown the phone : onPause onSaveInstanceState onStop
	 * 
	 * while start a themeDialogActivity onPause onSaveInstanceState
	 */

	@Override
	public void onAttachedToWindow() {
		super.onAttachedToWindow();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lifecycle_activity);
	}

	// onClick
	public void gotoSecondActivity(View view) {
		AllApplication.allDebug(this, "gotoSecondActivity");
		Intent intent = new Intent(this, LifecycleActivity2.class);
		startActivity(intent);
	}

	public void gotoThemeDialogActivity(View view) {
		AllApplication.allDebug(this, "gotoSecondActivity");
		Intent intent = new Intent(this, LifecycleActivity3.class);
		startActivity(intent);
	}

	public void startActivityForResult(View view) {
		AllApplication.allDebug(this, "gotoSecondActivity");
		Intent intent = new Intent(this, LifecycleActivity2.class);
		intent.putExtra("msg", "startActivityForResult");
		startActivityForResult(intent, 888);
	}

	public void forSystemExit(View view) {
		System.out.println("ActivityForLifecycle.forSystemExit()");
		AllApplication.shutDown();
		// useless
		// ActivityManager manager =
		// (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
		// manager.restartPackage(getPackageName());
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		System.out.println("ActivityForLifecycle.onActivityResult()");
		if (requestCode == 888) {
			if (data != null) {
				String result = data.getStringExtra("msg");
				if (result != null) {
					AllApplication.allDebug(ActivityForLifecycle.this, result);
				}
			}
		}
	}

}
