package com.zmm.androidallproject.multiThread;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MultiThreadActivityMain extends ListActivity {
	
	String[] stringArray = new String[]{"Handler","Asynctask"};
	ListView lv = null;
	Context context;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		context = getApplicationContext();
		lv = getListView();
		lv.setAdapter(new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, stringArray));
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				switch(position){
				case 0:
					Intent intent = new Intent(context,HandlerActivity.class);
					startActivity(intent);
					break;
				case 1:
					Intent intent1 = new Intent(context,AsynctaskActivity.class);
					startActivity(intent1);
					break;
				}
				
			}
		});
	}
	
}
