
package com.zmm.androidallproject.four_big_component.simpleUI;

import com.zmm.androidallproject.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;

public class MyAttrsView extends View {
	Paint mPaint;
	public MyAttrsView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mPaint = new Paint();
		TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.myView);
		int textColor = array.getColor(R.styleable.myView_textColor, 0XFF00FF00);
		
//		float textSize = array.getDimension(R.styleable.myView_textSize, 36);
		mPaint.setColor(textColor);
		mPaint.setTextSize(22);
		
		array.recycle();
	}

	public MyAttrsView(Context context) {
		super(context);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		mPaint.setStyle(Style.FILL);
		canvas.drawRect(10, 10, 100, 100, mPaint);
		
		mPaint.setColor(Color.BLUE);
		canvas.drawText("我是被画出来的,and the color attribute is from the 'attrs' file,it`s a collection of name and fomat value", 10, 120, mPaint);
	}
	
}
