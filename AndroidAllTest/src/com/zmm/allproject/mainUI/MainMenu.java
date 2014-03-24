package com.zmm.allproject.mainUI;

import com.zmm.allproject.R;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainMenu extends ListFragment {
	
	public static String[] menuArray = {"四大组件","多线程，同步","关于","退出"};
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.simple_list_single, null);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		SimpleStringListAdapter adapter = new SimpleStringListAdapter(getActivity());
		for(int i = 0; i < menuArray.length; i++){
			adapter.add(new SimpleListItem(menuArray[i], R.drawable.ic_icon));
		}
		
		setListAdapter(adapter);
		getListView().setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
//				Toast.makeText(getActivity(), arg2 + "", Toast.LENGTH_SHORT).show();
				switch (arg2) {
				case 3:
					System.exit(0);
					break;

				default:
					break;
				}
			}
		});
	}
	
	private class SimpleListItem{
		public String tag;
		public int iconRes;
		public SimpleListItem(String tag,int iconRes) {
			this.tag = tag;
			this.iconRes = iconRes;
		}
	}
	
	public class SimpleStringListAdapter extends ArrayAdapter<SimpleListItem>{

		public SimpleStringListAdapter(Context context) {
			super(context,0);
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if(convertView == null){
				convertView = LayoutInflater.from(getContext()).inflate(R.layout.simple_list_single_item, null);
			}
			ImageView icon = (ImageView) convertView.findViewById(R.id.imageViewListItem);
			icon.setImageResource(getItem(position).iconRes);
			TextView title = (TextView) convertView.findViewById(R.id.textViewListItem);
			title.setText(getItem(position).tag);
			
			return convertView;
		}
		
	}
}