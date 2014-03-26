package com.zmm.allproject.four_big_component.activity_lifecycle;

import com.zmm.allproject.R;
import com.zmm.allproject.mainUI.ListFragmentForConsole;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class ListFragmentForLifecycle extends ListFragmentForConsole {

	private static String[] strs = { "title 1", "title 2", "title 3",
			"title 4", "title 5", "title 6", "title 1", "title 2", "title 3",
			"title 4", "title 5", "title 6" };

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_for_lifecycle, null);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		getListView().setAdapter(
				new ArrayAdapter<String>(getActivity(),
						android.R.layout.simple_list_item_1, strs));
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
	}

}
