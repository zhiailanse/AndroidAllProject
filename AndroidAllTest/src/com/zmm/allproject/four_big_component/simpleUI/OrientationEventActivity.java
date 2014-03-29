package com.zmm.allproject.four_big_component.simpleUI;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.OrientationEventListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class OrientationEventActivity extends Activity {
	Context context = OrientationEventActivity.this;
	OrientationEventListener orientationEventListener = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TextView tv = new TextView(context);
		tv.setText("this page is for orientationEventListener!!!");
		setContentView(tv);
		
		orientationEventListener = new OrientationEventListener(context) {
			
			@Override
			public void onOrientationChanged(int orientation) {
				System.out.println("onOrientationChanged + " + orientation);
			}
		};
		
		if(orientationEventListener.canDetectOrientation()){
			orientationEventListener.enable();
		} else {
			orientationEventListener.disable();
			System.out.println("this page can`t dectect orientation change!!!");
		}
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		orientationEventListener.disable();
	}
	
}
