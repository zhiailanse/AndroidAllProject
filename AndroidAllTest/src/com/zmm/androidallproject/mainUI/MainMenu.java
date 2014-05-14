package com.zmm.androidallproject.mainUI;

import com.zmm.androidallproject.R;
import com.zmm.androidallproject.four_big_component.activity_lifecycle.MyPreferenceActivity;
import com.zmm.androidallproject.multiThread.MultiThreadActivityMain;
import com.zmm.androidallproject.widgets.WidgetsActivity1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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

public class MainMenu extends ListFragment {
	
	public static String[] menuArray = { "四大组件", "多线程，同步", "关于","设置", "退出","widgets1" };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}
	
	public interface OnItemOneClickListener{
		void onItemOneClick(); 
	}
	
	OnItemOneClickListener oneClickListener = null;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		oneClickListener = (OnItemOneClickListener) getActivity();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.simple_list_single, null);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		SimpleStringListAdapter adapter = new SimpleStringListAdapter(
				getActivity());
		for (int i = 0; i < menuArray.length; i++) {
			adapter.add(new SimpleListItem(menuArray[i], R.drawable.ic_icon));
		}

		setListAdapter(adapter);
		getListView().setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				switch (arg2) {
				case 0:
					if(oneClickListener != null){
						oneClickListener.onItemOneClick();
					}
					break;
				case 1:
					Intent intent2 = new Intent();
					intent2.setClass(getActivity(), MultiThreadActivityMain.class);
					startActivity(intent2);
					break;
				case 2:
					Intent intent = new Intent();
					intent.setClass(getActivity(), AboutActivity.class);
					startActivity(intent);
					break;

				case 3:
					Intent intent3 = new Intent();
					intent3.setClass(getActivity(), MyPreferenceActivity.class);
					startActivity(intent3);
					break;
				case 4:
					System.exit(0);
					break;
				case 5:
					Intent intent5 = new Intent();
					intent5.setClass(getActivity(), WidgetsActivity1.class);
					startActivity(intent5);
					break;
				default:
					break;
				}
			}
		});
	}

	private class SimpleListItem {
		public String tag;
		public int iconRes;

		public SimpleListItem(String tag, int iconRes) {
			this.tag = tag;
			this.iconRes = iconRes;
		}
	}

	public class SimpleStringListAdapter extends ArrayAdapter<SimpleListItem> {

		public SimpleStringListAdapter(Context context) {
			super(context, 0);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = LayoutInflater.from(getContext()).inflate(
						R.layout.simple_list_single_item, null);
			}
			ImageView icon = (ImageView) convertView
					.findViewById(R.id.imageViewListItem);
			icon.setImageResource(getItem(position).iconRes);
			TextView title = (TextView) convertView
					.findViewById(R.id.textViewListItem);
			title.setText(getItem(position).tag);

			return convertView;
		}

	}
}
