package com.zmm.allproject.mainUI;

import android.app.ActivityManager;
import android.app.Application;
import android.content.res.Configuration;
import android.os.Process;
import android.util.Log;

public class AllApplication extends Application {

	public static String All_Tag = "all_tag";

	public static void allDebug(Object obj, String s) {
		Log.d(All_Tag, obj.getClass().getSimpleName() + ": " + s);
	}

	@Override
	public void onCreate() {
		allDebug(this, "AllApplication OnCreate ..");
		super.onCreate();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		allDebug(this, "onConfigurationChanged");
		allDebug(this, "language is: " + newConfig.locale.getDisplayLanguage());
		super.onConfigurationChanged(newConfig);
	}

	public static void shutDown() {
		System.exit(0);
		// System.out.println(Process.myPid());
		// android.os.Process.killProcess(Process.myPid());
	}

}
