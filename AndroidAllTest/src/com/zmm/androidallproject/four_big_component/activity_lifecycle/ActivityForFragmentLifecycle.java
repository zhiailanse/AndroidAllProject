package com.zmm.androidallproject.four_big_component.activity_lifecycle;

import com.zmm.androidallproject.R;
import com.zmm.androidallproject.mainUI.ActivityForConsole;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class ActivityForFragmentLifecycle extends ActivityForConsole {
	/*
	 * lifecycle Activity onCreate() (transact f) f onAttach() f onCreate() f
	 * onViewCreated() f onActivityCreated() f onStart() Activity onStart()
	 * Activity onResume() f onResume()
	 * 
	 * f onPause() Activity onPause() f onStop() Activity onStop() f
	 * onDestoryView() f onDestory() f onDetach() Activity onDestory()
	 * 
	 * while fragment2 jump to another fragment from fragment1 (with backstack)
	 * f1 onPause() f1 onStop() f1 onDestoryView() (if without backstack )
	 * add:onDestroy() onDetach() f2 onAttach() f2 onCreate() f2 onViewCreated()
	 * f2 onActivityCreated() f2 onStart() f2 onResume()
	 * 
	 * click back: f2 onPause() f2 onStop() f2 onDestoryView() f2 onDestory() f2
	 * onDetach() f1 onViewCreated() f1 onActivityCreated() f1 onStart() f1
	 * onResume()
	 */

	TextFragment textFragment = new TextFragment();
	ListFragmentForLifecycle listFragmentForLifecycle = new ListFragmentForLifecycle();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.for_activity_contains_fragment);

	}

	@Override
	protected void onStart() {
		super.onStart();

		transact(R.id.container1, listFragmentForLifecycle);

		Bundle b = new Bundle();
		b.putString("name", "the orignal Fragment");
		textFragment.setArguments(b);
		transact(R.id.container2, textFragment);

	}

	@Override
	protected void onResume() {
		super.onResume();
		listFragmentForLifecycle.getListView().setOnItemClickListener(
				new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						TextFragment2 textFragment = new TextFragment2();
						Bundle b = new Bundle();
						b.putString("name", "Fragment" + arg2);
						textFragment.setArguments(b);
						transactWithBackStack(R.id.container2, textFragment);
					}
				});
	}

	private void transactWithBackStack(int layoutId, Fragment f) {
		getSupportFragmentManager().beginTransaction().replace(layoutId, f)
				.addToBackStack(null).commit();
	}

	private void transact(int layoutId, Fragment f) {
		getSupportFragmentManager().beginTransaction().replace(layoutId, f)
				.commit();
	}

}
