package com.zmm.allproject.four_big_component.activity_lifecycle;

import android.os.Bundle;

import com.zmm.allproject.R;
import com.zmm.allproject.mainUI.ActivityForConsole;

public class ForUI extends ActivityForConsole {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setDebug(false);
		
		setContentView(R.layout.forui_layout);
	}
}
