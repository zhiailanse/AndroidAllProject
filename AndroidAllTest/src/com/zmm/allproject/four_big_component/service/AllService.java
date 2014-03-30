package com.zmm.allproject.four_big_component.service;

import com.zmm.allproject.four_big_component.BR.MyBroadcaseRecever;
import com.zmm.allproject.mainUI.AllApplication;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Binder;
import android.os.IBinder;

/*
 * service liftcycle
 * onCreate()
 * onStartCommand()
 * onBind()
 * 
 */
public class AllService extends Service {

	public static String AllServiceAction = "com.zmm.allproject.AllService";
	public static String Tag = "AllService";
	public IBinder service;
	
	@Override
	public void onCreate() {
		AllApplication.allDebug(this, "onCreate");
//		Intent intent = new Intent(MyBroadcaseRecever.BRActionOne);
//		intent.putExtra("msg", "msg1");
//		sendStickyBroadcast(intent);
//		sendBroadcast(intent);
//		sendOrderedBroadcast(intent, "com.zmm.allproject.permission");
		super.onCreate();
		
		System.out.println("AllService.onCreate()");
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		System.out.println("AllService.onConfigurationChanged()");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		AllApplication.allDebug(this, "onDestroy");
//		Intent intent = new Intent(MyBroadcaseRecever.BRActionOne);
//		intent.putExtra("msg", "msg2");
//		sendBroadcast(intent);
		
		System.out.println("AllService.onDestroy()");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		System.out.println("AllService.onStartCommand()");
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public boolean onUnbind(Intent intent) {
		System.out.println("AllService.onUnbind()");
		return super.onUnbind(intent);
	}
	
	public String getMsg(){
		return "this msg is from AllService,from IBinder!";
	}

	@Override
	public IBinder onBind(Intent arg0) {
		System.out.println("AllService.onBind()");
		if(service == null){
			service = new ServiceBinder();
		}
		return service;
	}

	@Override
	public void onRebind(Intent intent) {
		super.onRebind(intent);
		System.out.println("AllService.onRebind()");
	}

	@SuppressLint("NewApi")
	@Override
	public void onTaskRemoved(Intent rootIntent) {
		super.onTaskRemoved(rootIntent);
	}
	
	public class ServiceBinder extends Binder{
		public AllService getService(){
			return AllService.this;
		}
	}

}
