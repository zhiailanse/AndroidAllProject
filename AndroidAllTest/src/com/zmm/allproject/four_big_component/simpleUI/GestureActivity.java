package com.zmm.allproject.four_big_component.simpleUI;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.LinearLayout;

public class GestureActivity extends Activity {
	Context context = GestureActivity.this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(new GameAreaView(getApplicationContext()));
	}
	
}
