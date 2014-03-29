package com.zmm.allproject.four_big_component.simpleUI;

import com.zmm.allproject.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.animation.OvershootInterpolator;

public class GameAreaView extends View {
	
	public GameAreaView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
//		gestureDetector.setOnDoubleTapListener(doubleTap);
	}
	
	public GameAreaView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public GameAreaView(Context context) {
		super(context);
		init(context);
	}

	public void init(Context context){
		translate = new Matrix();
		GestureListener listener = new GestureListener(this);
//		ScaleGestureListener scalelistener = new ScaleGestureListener(this);
		gestureDetector = new GestureDetector(context, listener, null, true);
//		scaleGestureDetector = new ScaleGestureDetector(context, listener);
		droid = BitmapFactory.decodeResource(getResources(), R.drawable.icon);
	}
	
	public void onScale(float factor){
		translate.preScale(factor, factor);
		System.out.println("factor ----------" + factor);
		invalidate();
	}
	
	public Bitmap getDroid() {
		return droid;
	}
	public void setDroid(Bitmap droid) {
		this.droid = droid;
	}

	private static final String DEBUG_TAG = "SimpleGesture->GameAreaView";
	private GestureDetector gestureDetector;
	private ScaleGestureDetector scaleGestureDetector;
	private Matrix translate;
	private Bitmap droid;
	private Matrix aninmateStart;
	private OvershootInterpolator animateInterpolator;
	private long startTime;
	private long endTime;
	private float totalAnimDx;
	private float totalAnimDy;

//	public GameAreaView(Context context,int iGraphicResourceId) {
//		super(context);
//		translate = new Matrix();
//		GestureListener listener = new GestureListener(this);
//		gestureDetector = new GestureDetector(context, listener, null, true);
//		droid = BitmapFactory.decodeResource(getResources(), iGraphicResourceId);
//	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		System.out.println("GameAreaView.onTouchEvent()");
		boolean retVal = false;
		retVal = gestureDetector.onTouchEvent(event);
//		retVal = scaleGestureDetector.onTouchEvent(event);
		return retVal;
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawBitmap(droid, translate, null);
	}
	
	public void onResetLocation(){
		translate.reset();
		invalidate();
	}
	
	public void onMove(float dx,float dy){
		translate.postTranslate(dx, dy);
		invalidate();
	}
	
	public void onAnimateMove(float dx,float dy,long duration){
		aninmateStart = new Matrix(translate);
		animateInterpolator = new OvershootInterpolator();
		startTime = System.currentTimeMillis();
		endTime = startTime + duration;
		totalAnimDx = dx;
		totalAnimDy = dy;
		post(new Runnable() {
			
			@Override
			public void run() {
				onAnimateStep();
			}
		});
	}
	
	private void onAnimateStep(){
		long curTime = System.currentTimeMillis();
		float percentTime = (float) (curTime - startTime) / (float) (endTime - startTime);
		float percentDistance = animateInterpolator.getInterpolation(percentTime);
		float curDx = percentDistance * totalAnimDx;
		float curDy = percentDistance * totalAnimDy;
		translate.set(aninmateStart);
		onMove(curDx, curDy);
		
		if(percentTime < 1.0f){
			post(new Runnable() {
				
				@Override
				public void run() {
					onAnimateStep();
				}
			});
		}
	}
	
}
