package com.zmm.allproject.four_big_component.simpleUI;

import com.zmm.allproject.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class MatrixAndDrawViewActivity extends Activity {
	Context context = MatrixAndDrawViewActivity.this;
	MyAttrsView myView = null;
	ImageView image;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gesture_activity);
		
		myView = (MyAttrsView) findViewById(R.id.myAttrsView);
		image = (ImageView) findViewById(R.id.image);
	}
	
	public void translate(View v){
		Bitmap bitmapOrg = BitmapFactory.decodeResource(getResources(), R.drawable.ic_icon);
		int width = bitmapOrg.getWidth();
		int height = bitmapOrg.getHeight();
		System.out.println("width = " + width + " height = " + height);
		
		int newWidth = 200;
		int newHeight = 200;
		
		float scaleWidth = ((float)newWidth)/width;
		float scaleHeight = ((float)newHeight) / height;
		
		Matrix matrix = new Matrix();
		
		matrix.postScale(scaleWidth, scaleHeight);
		
		matrix.postRotate(45);
		Bitmap resizedBitmap = Bitmap.createBitmap(bitmapOrg, 0, 0, width, height,matrix,true);
		BitmapDrawable bmd = new BitmapDrawable(getResources(), resizedBitmap);
		image.setImageDrawable(bmd);
		
		image.setScaleType(ScaleType.CENTER);
	}
	
}
