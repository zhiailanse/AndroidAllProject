package com.zmm.androidallproject.four_big_component;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;

import com.zmm.androidallproject.R;
import com.zmm.androidallproject.four_big_component.activity_lifecycle.ActivityForFragmentLifecycle;
import com.zmm.androidallproject.four_big_component.activity_lifecycle.ActivityForLifecycle;
import com.zmm.androidallproject.four_big_component.activity_lifecycle.DispatchKeyEventActivity;
import com.zmm.androidallproject.four_big_component.activity_lifecycle.MyPreferenceActivity;
import com.zmm.androidallproject.four_big_component.activity_lifecycle.NotificationActivity;
import com.zmm.androidallproject.four_big_component.activity_lifecycle.ViewFlipperActivity;
import com.zmm.androidallproject.four_big_component.activity_lifecycle.WebViewActivity;
import com.zmm.androidallproject.four_big_component.simpleUI.ForUI;
import com.zmm.androidallproject.four_big_component.simpleUI.GestureActivity;
import com.zmm.androidallproject.four_big_component.simpleUI.MatrixAndDrawViewActivity;
import com.zmm.androidallproject.four_big_component.simpleUI.OrientationEventActivity;
import com.zmm.androidallproject.four_big_component.simpleUI.SoftkeyboardAndUserEventActivity;
import com.zmm.androidallproject.mainUI.ListFragmentForConsole;

public class Fragment4Activity extends ListFragmentForConsole {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.activity_contain_fragment, null);
	}

	String[] objects = { "forActivityLifecycle", "toPreferencesActivity",
			"forFragmentLifecycle", "simpleUIs" 
			,"SoftkeyboardAndUserEvent"
			,"GestureActivity"
			,"MatrixAndDrawViewActivity"
			,"OrientationEventActivity"
			,"Notification"
			,"DispatchKeyEventActivity"
			,"viewFlipperActivity"
			,"WebViewActivity"
			};

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		getListView()
				.setAdapter(
						new ArrayAdapter<String>(getActivity(),
								android.R.layout.simple_list_item_activated_1,
								objects));

		getListView().setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				switch (arg2) {
				case 0:
					Intent intent = new Intent(getActivity(),
							ActivityForLifecycle.class);
					startActivity(intent);
					break;

				case 1:
					Intent intent2 = new Intent(getActivity(),
							MyPreferenceActivity.class);
					startActivity(intent2);
					break;
				case 2:
					Intent intent3 = new Intent(getActivity(),
							ActivityForFragmentLifecycle.class);
					startActivity(intent3);
					break;
				case 3:
					Intent intent4 = new Intent(getActivity(), ForUI.class);
					startActivity(intent4);
					break;
				case 4:
					Intent intent5 = new Intent(getActivity(), SoftkeyboardAndUserEventActivity.class);
					startActivity(intent5);
					break;
				case 5:
					Intent intent6 = new Intent(getActivity(), GestureActivity.class);
					startActivity(intent6);
					break;
				case 6:
					Intent intent7 = new Intent(getActivity(), MatrixAndDrawViewActivity.class);
					startActivity(intent7);
					break;
				case 7:
					Intent intent8 = new Intent(getActivity(), OrientationEventActivity.class);
					startActivity(intent8);
					break;
				case 8:
					Intent intent9 = new Intent(getActivity(), NotificationActivity.class);
					startActivity(intent9);
					break;
				case 9:
					Intent intent10 = new Intent(getActivity(), DispatchKeyEventActivity.class);
					startActivity(intent10);
					break;
				case 10:
					Intent intent11 = new Intent(getActivity(), ViewFlipperActivity.class);
					startActivity(intent11);
					break;
				case 11:
					Intent intent12 = new Intent(getActivity(), WebViewActivity.class);
					startActivity(intent12);
					break;
				default:
					break;
				}

			}
		});
	}

}
