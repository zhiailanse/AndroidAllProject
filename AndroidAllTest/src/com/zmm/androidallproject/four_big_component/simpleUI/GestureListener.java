package com.zmm.androidallproject.four_big_component.simpleUI;

import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;

public class GestureListener extends SimpleOnGestureListener{

	GameAreaView view;
	
	public GestureListener(GameAreaView view) {
		this.view = view;
	}

	@Override
	public boolean onDoubleTap(MotionEvent e) {
		System.out.println("GestureListener.onDoubleTap()");
		view.onResetLocation();
		return true;
	}

	@Override
	public boolean onDoubleTapEvent(MotionEvent e) {
		System.out.println("GestureListener.onDoubleTapEvent()");
		return super.onDoubleTapEvent(e);
	}

	@Override
	public boolean onDown(MotionEvent e) {
		System.out.println("GestureListener.onDown()");
		return true;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		System.out.println("GestureListener.onFling()");
		
		final float distanceTimeFactor = 0.4f;
		final float totalDx = (distanceTimeFactor * velocityX / 2);
		final float totalDy = (distanceTimeFactor * velocityY / 2);
		view.onAnimateMove(totalDx, totalDy, (long) (1000 * distanceTimeFactor));
		
		return true;
	}

	@Override
	public void onLongPress(MotionEvent e) {
		super.onLongPress(e);
		System.out.println("GestureListener.onLongPress()");
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		System.out.println("GestureListener.onScroll()");
		view.onMove(-distanceX, -distanceY);
		return true;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		super.onShowPress(e);
		System.out.println("GestureListener.onShowPress()");
	}

	@Override
	public boolean onSingleTapConfirmed(MotionEvent e) {
		System.out.println("GestureListener.onSingleTapConfirmed()");
		return super.onSingleTapConfirmed(e);
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		System.out.println("GestureListener.onSingleTapUp()");
		return super.onSingleTapUp(e);
	}
	
}
