package com.zmm.androidallproject.four_big_component.activity_lifecycle;

import com.zmm.androidallproject.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class ViewFlipperActivity extends Activity {
	
	private ViewFlipper viewFlipper;
	TextView tv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.view_flipper_layout);
		viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
		tv = (TextView) findViewById(R.id.textViewViewFlipper01);
		
		
		tv.setText("3333333");
	}
	
	public void LeftClick(View v){
		viewFlipper.showPrevious();
	}
	
	public void RightClick(View v){
		viewFlipper.showNext();
	}
}
