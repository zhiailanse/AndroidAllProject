package com.zmm.allproject.four_big_component.service;

import java.security.AllPermission;

import com.zmm.allproject.mainUI.AllApplication;

import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.IBinder;

public class AllService extends Service {

	public static String AllServiceAction = "com.zmm.allproject.AllService";

	@Override
	public void onCreate() {
		AllApplication.allDebug(this, "onCreate");
		super.onCreate();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		AllApplication.allDebug(this, "onDestroy");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public boolean onUnbind(Intent intent) {
		return super.onUnbind(intent);
	}

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

}
