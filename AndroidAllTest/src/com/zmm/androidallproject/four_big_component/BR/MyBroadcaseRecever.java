package com.zmm.androidallproject.four_big_component.BR;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;

public class MyBroadcaseRecever extends BroadcastReceiver {
	
	public static String BRActionOne = "com.zmm.androidallproject.MyBroadcaseRecever";

	@Override
	public void onReceive(Context context, Intent intent) {
		System.out.println("--"+intent.getAction());
		String s = Intent.ACTION_SCREEN_OFF;
		
//		try {
//			System.out.println("thread id : "+Thread.currentThread().getId());
//			Thread.currentThread().sleep(5000);
//			
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		
		String msg = intent.getStringExtra("msg");
		if(msg != null && msg.equals("msg1")){
			System.out.println(")))))))))receive msg1))))))))))");
		}else if(msg != null && msg.equals("msg2")){
			System.out.println(")))))))))receive msg2))))))))))");
		}
		
		if(intent.getAction().equals(Intent.ACTION_AIRPLANE_MODE_CHANGED)){
			System.out.println(Intent.ACTION_AIRPLANE_MODE_CHANGED);
			System.out.println(Settings.System.getInt(context.getContentResolver(),
                Settings.System.AIRPLANE_MODE_ON, 0) + "-");
		}
		
	}

}
