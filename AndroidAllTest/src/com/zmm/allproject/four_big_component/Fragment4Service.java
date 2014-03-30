package com.zmm.allproject.four_big_component;

import com.zmm.allproject.R;
import com.zmm.allproject.aidl.AIDLService;
import com.zmm.allproject.four_big_component.service.AIDLServiceEntity;
import com.zmm.allproject.four_big_component.service.AllService;
import com.zmm.allproject.mainUI.ListFragmentForConsole;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;

public class Fragment4Service extends ListFragmentForConsole implements ServiceConnection{

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.activity_contain_fragment, null);
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Intent serviceIntent = new Intent(AllService.AllServiceAction);
		getActivity().stopService(serviceIntent);
	}

	@Override
	public void onDetach() {
		super.onDetach();
	}

	@Override
	public void onPause() {
		super.onPause();
	}

	@Override
	public void onStop() {
		super.onStop();
	}

	String[] objects = { "startService","stopService","bindService","unBindService","startAIDL","stopAIDL" };

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		getListView()
				.setAdapter(
						new ArrayAdapter<String>(getActivity(),
								android.R.layout.simple_list_item_activated_1,
								objects));

		getListView().setOnItemClickListener(new OnItemClickListener() {
			Intent serviceIntent = new Intent(AllService.AllServiceAction);

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				switch (arg2) {
				case 0:
//					start service
					getActivity().startService(serviceIntent);
					break;
				case 1:
					getActivity().stopService(serviceIntent);
					break;
				case 2:
					getActivity().bindService(serviceIntent, Fragment4Service.this, Context.BIND_AUTO_CREATE);
					break;
				case 3:
					try {
						getActivity().unbindService(Fragment4Service.this);
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				case 4:
					serviceIntent = new Intent(AIDLServiceEntity.AidlServiceAction);
					getActivity().bindService(serviceIntent, aidlConnection, Context.BIND_AUTO_CREATE);
					break;	
				case 5:
					getActivity().unbindService(aidlConnection);
					break;	
				default:
					
					break;
				}

			}
		});
	}
	
	ServiceConnection aidlConnection = new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			System.out.println("Fragment4Service.onServiceDisconnected()");
		}
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			System.out
					.println("Fragment4Service.aidlConnection.new ServiceConnection() {...}.onServiceConnected()");
//			AIDLService.Stub stub = (AIDLService.Stub) service;
			AIDLService stub = AIDLService.Stub.asInterface(service);
			
			try {
				System.out.println(stub.sayHello());
				System.out.println(stub.getBook());
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	};
	
	AllService service = null;

	@Override
	public void onServiceConnected(ComponentName name, IBinder service) {
		System.out.println("Fragment4Service.onServiceConnected()");
		this.service = ((AllService.ServiceBinder)service).getService();
		System.out.println(this.service.getMsg());
	}

	@Override
	public void onServiceDisconnected(ComponentName name) {
		System.out.println("Fragment4Service.onServiceDisconnected()");
		service = null;
	}

}
