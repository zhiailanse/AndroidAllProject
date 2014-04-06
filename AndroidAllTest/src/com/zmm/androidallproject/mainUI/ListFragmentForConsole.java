package com.zmm.androidallproject.mainUI;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ListFragmentForConsole extends ListFragment {
	protected boolean debug = false;

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (debug) {
			System.out.println(this.getClass().getSimpleName() + "---"
					+ new Exception().getStackTrace()[1].getMethodName());
		}
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		if (debug) {
			System.out.println(this.getClass().getSimpleName() + "---"
					+ new Exception().getStackTrace()[1].getMethodName());
		}
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		if (debug) {
			System.out.println(this.getClass().getSimpleName() + "---"
					+ new Exception().getStackTrace()[1].getMethodName());
		}
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		// if(debug){
		// System.out.println(this.getClass().getSimpleName() + "---" + new
		// Exception().getStackTrace()[1].getMethodName());
		// }
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		// if(debug){
		// System.out.println(this.getClass().getSimpleName() + "---" + new
		// Exception().getStackTrace()[1].getMethodName());
		// }
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (debug) {
			System.out.println(this.getClass().getSimpleName() + "---"
					+ new Exception().getStackTrace()[1].getMethodName());
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (debug) {
			System.out.println(this.getClass().getSimpleName() + "---"
					+ new Exception().getStackTrace()[1].getMethodName());
		}
	}

	@Override
	public void onDetach() {
		super.onDetach();
		// if(debug){
		// System.out.println(this.getClass().getSimpleName() + "---" + new
		// Exception().getStackTrace()[1].getMethodName());
		// }
	}

	@Override
	public void onPause() {
		super.onPause();
		if (debug) {
			System.out.println(this.getClass().getSimpleName() + "---"
					+ new Exception().getStackTrace()[1].getMethodName());
		}
	}

	@Override
	public void onResume() {
		super.onResume();
		if (debug) {
			System.out.println(this.getClass().getSimpleName() + "---"
					+ new Exception().getStackTrace()[1].getMethodName());
		}
	}

	@Override
	public void onStart() {
		super.onStart();
		if (debug) {
			System.out.println(this.getClass().getSimpleName() + "---"
					+ new Exception().getStackTrace()[1].getMethodName());
		}
	}

	@Override
	public void onStop() {
		super.onStop();
		if (debug) {
			System.out.println(this.getClass().getSimpleName() + "---"
					+ new Exception().getStackTrace()[1].getMethodName());
		}
	}

}
