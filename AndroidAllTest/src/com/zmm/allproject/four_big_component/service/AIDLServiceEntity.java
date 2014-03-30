package com.zmm.allproject.four_big_component.service;

import com.zmm.allproject.aidl.AIDLService;
import com.zmm.allproject.aidl.Book;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class AIDLServiceEntity extends Service {
	
	public static String AidlServiceAction = "com.zmm.allproject.four_big_component.service.AIDLServiceEntity";
	
	private AIDLService.Stub mBinder = new AIDLService.Stub() {
		
		@Override
		public String sayHello() throws RemoteException {
			return "hello!!";
		}
		
		@Override
		public Book getBook() throws RemoteException {
			Book book = new Book();
			book.setBookName("舒化奶");
			book.setBookPrice(38);
			return book;
		}
	};
	
	@Override
	public void onCreate() {
		super.onCreate();
		System.out.println("AIDLServiceEntity.onCreate()");
	}

	@Override
	public IBinder onBind(Intent intent) {
		System.out.println("AIDLServiceEntity.onBind()");
		return mBinder;
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		System.out.println("AIDLServiceEntity.onDestroy()");
	}

}
