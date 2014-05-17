package com.zmm.androidallproject.four_big_component.activity_lifecycle;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class PendingBroadcastReceiver extends BroadcastReceiver{

	String action = "PendingBroadcastReceiver";
	@Override
	public void onReceive(Context context, Intent intent) {
		if(intent.getAction().equals(action)){
			Toast.makeText(context, "onReceive!!!", 0).show();
		}
	}

}
