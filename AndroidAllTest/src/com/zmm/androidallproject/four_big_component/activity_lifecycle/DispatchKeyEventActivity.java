package com.zmm.androidallproject.four_big_component.activity_lifecycle;

import com.zmm.androidallproject.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;

public class DispatchKeyEventActivity extends Activity{
	
	ListView list1 = null;
	GridView list2 = null;
	
	String[] array = {"1","2","3","4","5","6","7","8","1","2","3","4","5","6","7","8"};
	Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dispatch_key_event_layout);
		context  = getApplicationContext();
		init();
		
		
	}
	
	public void init(){
		list1 = (ListView) findViewById(R.id.listMy1);
		list2 = (GridView) findViewById(R.id.gridMy2);
		
		list1.setAdapter(new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, array));
		list2.setAdapter(new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, array));
	}
}
