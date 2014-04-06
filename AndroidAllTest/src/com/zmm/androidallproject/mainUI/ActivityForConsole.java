package com.zmm.androidallproject.mainUI;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class ActivityForConsole extends FragmentActivity {
	protected boolean debug = true;

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	@SuppressLint("NewApi")
	@Override
	public void onAttachFragment(Fragment fragment) {
		super.onAttachFragment(fragment);
		if (debug) {
			System.out.println(this.getClass().getSimpleName() + "---"
					+ new Exception().getStackTrace()[1].getMethodName());
		}
	}

	@Override
	public void onAttachedToWindow() {
		if (debug) {
			System.out.println(this.getClass().getSimpleName() + "---"
					+ new Exception().getStackTrace()[1].getMethodName());
		}
		super.onAttachedToWindow();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (debug) {
			System.out.println(this.getClass().getSimpleName() + "---"
					+ new Exception().getStackTrace()[1].getMethodName());
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (debug) {
			System.out.println(this.getClass().getSimpleName() + "---"
					+ new Exception().getStackTrace()[1].getMethodName());
		}
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		if (debug) {
			System.out.println(this.getClass().getSimpleName() + "---"
					+ new Exception().getStackTrace()[1].getMethodName());
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (debug) {
			System.out.println(this.getClass().getSimpleName() + "---"
					+ new Exception().getStackTrace()[1].getMethodName());
		}
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		if (debug) {
			System.out.println(this.getClass().getSimpleName() + "---"
					+ new Exception().getStackTrace()[1].getMethodName());
		}
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		if (debug) {
			System.out.println(this.getClass().getSimpleName() + "---"
					+ new Exception().getStackTrace()[1].getMethodName());
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (debug) {
			System.out.println(this.getClass().getSimpleName() + "---"
					+ new Exception().getStackTrace()[1].getMethodName());
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		if (debug) {
			System.out.println(this.getClass().getSimpleName() + "---"
					+ new Exception().getStackTrace()[1].getMethodName());
		}
	}

	@Override
	protected void onStart() {
		super.onStart();
		if (debug) {
			System.out.println(this.getClass().getSimpleName() + "---"
					+ new Exception().getStackTrace()[1].getMethodName());
		}
	}

	@Override
	protected void onStop() {
		super.onStop();
		if (debug) {
			System.out.println(this.getClass().getSimpleName() + "---"
					+ new Exception().getStackTrace()[1].getMethodName());
		}
	}

}
