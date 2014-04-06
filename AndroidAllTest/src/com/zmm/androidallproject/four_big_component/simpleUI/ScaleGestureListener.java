package com.zmm.androidallproject.four_big_component.simpleUI;

import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.OnScaleGestureListener;

public class ScaleGestureListener implements OnScaleGestureListener{

	GameAreaView view;
	
	public ScaleGestureListener(GameAreaView view) {
		this.view = view;
	}

	//多点触控
	@Override
	public boolean onScale(ScaleGestureDetector detector) {
		// TODO Auto-generated method stub
		float scale = detector.getScaleFactor();
		view.onScale(scale);
		System.out.println("GestureListener.onScale()");
		return true;
	}

	@Override
	public boolean onScaleBegin(ScaleGestureDetector detector) {
		// TODO Auto-generated method stub
		System.out.println("ScaleGestureListener.onScaleBegin()");
		return true;
	}

	@Override
	public void onScaleEnd(ScaleGestureDetector detector) {
		// TODO Auto-generated method stub
		System.out.println("ScaleGestureListener.onScaleEnd()");
	}
	
}
