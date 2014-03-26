package com.zmm.allproject.four_big_component;

import com.zmm.allproject.R;
import com.zmm.allproject.R.layout;
import com.zmm.allproject.four_big_component.activity_lifecycle.ActivityForLifecycle;
import com.zmm.allproject.four_big_component.activity_lifecycle.MyPreferenceActivity;
import com.zmm.allproject.mainUI.ListFragmentForConsole;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;

public class Fragment4Broadcast extends ListFragmentForConsole {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.activity_contain_fragment, null);
	}

	String[] objects = { "this is for BroadcastReceiver" };

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
				default:
					break;
				}

			}
		});
	}

}
