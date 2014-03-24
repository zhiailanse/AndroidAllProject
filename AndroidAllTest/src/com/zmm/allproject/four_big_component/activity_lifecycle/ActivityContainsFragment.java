package com.zmm.allproject.four_big_component.activity_lifecycle;

import com.zmm.allproject.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class ActivityContainsFragment extends FragmentActivity {
	
	TextFragment textFragment = new TextFragment();
	ListFragmentForLifecycle listFragmentForLifecycle = new ListFragmentForLifecycle();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.for_activity_contains_fragment);
		
		transact(R.id.container1, listFragmentForLifecycle);
		
		Bundle b = new Bundle();
		b.putString("name", "the orignal Fragment");
		textFragment.setArguments(b);
		transact(R.id.container2, textFragment);
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		listFragmentForLifecycle.getListView().setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				TextFragment textFragment = new TextFragment();
				Bundle b = new Bundle();
				b.putString("name", "Fragment" + arg2);
				textFragment.setArguments(b);
				transactWithBackStack(R.id.container2, textFragment);
			}
		});
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
	}
	
	private void transactWithBackStack(int layoutId,Fragment f){
		getSupportFragmentManager()
		.beginTransaction()
		.replace(layoutId, f)
		.addToBackStack(null)
		.commit();
	}
	
	private void transact(int layoutId,Fragment f){
		getSupportFragmentManager()
		.beginTransaction()
		.replace(layoutId, f)
		.commit();
	}

}
