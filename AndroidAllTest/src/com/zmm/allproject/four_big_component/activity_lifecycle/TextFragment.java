package com.zmm.allproject.four_big_component.activity_lifecycle;

import com.zmm.allproject.mainUI.FragmentForConsole;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TextFragment extends FragmentForConsole {
	
	String name = "blank";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		if(getArguments() != null){
			String name = getArguments().getString("name");
			if(name != null){
				this.name = name;
			}
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		TextView tv = new TextView(getActivity());
		StringBuffer sb = new StringBuffer();
		sb.append(name);
		tv.setText(sb.toString());
		return tv;
	}
	
}
