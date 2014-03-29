package com.zmm.allproject.four_big_component.service;

import com.zmm.allproject.four_big_component.Fragment4Broadcast;
import com.zmm.allproject.four_big_component.BR.MyBroadcaseRecever;
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
		Intent intent = new Intent(MyBroadcaseRecever.BRActionOne);
		intent.putExtra("msg", "msg1");
//		sendStickyBroadcast(intent);
//		sendBroadcast(intent);
		sendOrderedBroadcast(intent, "com.zmm.allproject.permission");
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
		Intent intent = new Intent(MyBroadcaseRecever.BRActionOne);
		intent.putExtra("msg", "msg2");
		sendBroadcast(intent);
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
