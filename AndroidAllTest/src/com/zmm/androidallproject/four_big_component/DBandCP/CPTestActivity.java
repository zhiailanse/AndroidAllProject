package com.zmm.androidallproject.four_big_component.DBandCP;

import java.util.ArrayList;
import java.util.List;

import com.zmm.androidallproject.R;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class CPTestActivity extends Activity{
	EditText editName;
	EditText editAge;
	EditText editPhone;
	Context context;
	ListView listView;
	List<People4CP> list = new ArrayList<People4CP>();
	private Handler queryHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch(msg.what){
			case 1:
				listAdapter.notifyDataSetChanged();
				break;
			}
		};
	};
	ListAdapter listAdapter ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.content_provider_activity);
		init();
		
	}
	
	Cursor c ;
	
	class queryRunable implements Runnable{

		@Override
		public void run() {
			c = queryTheCP();
			list = getListFromCursor(c);
			queryHandler.sendEmptyMessage(1);
			if(c != null && !c.isClosed()){
				c.close();
			}
		}
	}
	
	private ArrayList<People4CP> getListFromCursor(Cursor c){
		List<People4CP> list = new ArrayList<People4CP>();
		if(c != null){
			if(c.moveToFirst()){
			do{
				String _id = c.getString(c.getColumnIndex(MyProvider._ID));
				String name = c.getString(c.getColumnIndex(MyProvider.NAME));
				String age = c.getString(c.getColumnIndex(MyProvider.AGE));
				String phone = c.getString(c.getColumnIndex(MyProvider.PHONE));
				People4CP people4cp = new People4CP(name, age, phone, _id);
				System.out.println("----"+people4cp);
				list.add(people4cp);
				}while(c.moveToNext());
			}
		}
		return (ArrayList<People4CP>) list;
	}
	
	private Cursor queryTheCP(){
		String[] projection = {MyProvider._ID,MyProvider.NAME,MyProvider.AGE,MyProvider.PHONE};
		Cursor c = context.getContentResolver().query(MyProvider.content_URI, projection, null, null, MyProvider.DEFAULT_SORT_ORDER);
		if(c != null){
			System.out.println("---"+c.getCount());
		}else{
			System.out.println("c == null");
		}
		return c;
	}
	
	private class ListViewHolder{
		TextView name;
		TextView _id;
		TextView age;
		TextView phone;
	}
	
	class ListAdapter extends BaseAdapter{
		Context context;
		ListViewHolder holer;
		People4CP people4cp ;
		public ListAdapter(Context context) {
			this.context = context;
			holer = new ListViewHolder();
			people4cp = new People4CP();
		}
		
		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			people4cp = list.get(position);
			int position1 = Integer.parseInt(people4cp.get_id());
			
			return (long)position1;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if(convertView == null){
				LayoutInflater inflater = LayoutInflater.from(context);
				convertView = inflater.inflate(R.layout.cp_list_item, null);
				holer._id = (TextView) convertView.findViewById(R.id.textViewId);
				holer.name = (TextView) convertView.findViewById(R.id.textViewName);
				holer.age = (TextView) convertView.findViewById(R.id.textViewAge);
				holer.phone = (TextView) convertView.findViewById(R.id.textViewPhone);
			}
			people4cp = list.get(position);
			holer._id.setText(people4cp.get_id());
			holer.name.setText(people4cp.getName());
			holer.age.setText(people4cp.getAge());
			holer.phone.setText(people4cp.getPhone());
			
			return convertView;
		}
		
	}
	
	private void init(){
		context = getApplicationContext();
		editName = (EditText) findViewById(R.id.editTextName);
		editAge = (EditText) findViewById(R.id.editTextAge);
		editPhone = (EditText) findViewById(R.id.editTextPhone);
		listView = (ListView) findViewById(R.id.listViewCP);
		listAdapter = new ListAdapter(context);
		listView.setAdapter(listAdapter);
		
		startQueryThread();
	}
	
	private void startQueryThread(){
		new Thread(new queryRunable()).start();
	}
	
	//onClick
	public void buttonCommit(View v){
		String name = editName.getText().toString().trim();
		String age = editAge.getText().toString().trim();
		String phone = editPhone.getText().toString().trim();
		if(!name.equals("") && !age.equals("") && !phone.equals("")){
			insert(name, age, phone);
		}else{
			Toast.makeText(context, "请填写完整!", 0).show();
		}
	}
	
	public void insert(String name,String age,String phone){
		ContentValues values = new ContentValues();
		values.put(MyProvider.NAME, name);
		values.put(MyProvider.AGE, age);
		values.put(MyProvider.PHONE, phone);
		System.out.println("---"+MyProvider.content_URI);
		context.getContentResolver().insert(MyProvider.content_URI, values);
		
		startQueryThread();
	}
	
}
