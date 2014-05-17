package com.zmm.androidallproject.multiThread;

import com.zmm.androidallproject.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Toast;

/**
 * 每个线程都持有一个looper私有变量，
 * looper持有消息队列MessageQueue对象
 * Handler作用就是封装封装发送消息和处理消息的过程
 * @author zhang
 *
 */
public class HandlerActivity extends Activity {
	Context context;
	MyHandler handler = new MyHandler(Looper.myLooper());
	Handler handler2 = null;
	
	
	class MyHandler extends Handler{
		public MyHandler(Looper myLooper) {
			super(myLooper);
		}

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 88:
				Toast.makeText(context, "dd", 0).show();
				break;

			default:
				break;
			}
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.handler_layout);
		context = getApplicationContext();
		
		MyThread2 myThread = new MyThread2();
		myThread.start();
		
	}
	
	class MyThread2 extends Thread{
		
		@Override
		public void run() {
			super.run();
			Looper.prepare();
			handler2 = new Handler(Looper.myLooper()){
				@Override
				public void handleMessage(Message msg) {
					super.handleMessage(msg);
					if(msg.what == 333){
						Toast.makeText(context, "333", 0).show();
					}
				}
			};
			Looper.loop();
		}
	}
	
	class MyThread extends Thread{
		@Override
		public void run() {
			super.run();
			try {
				Thread.sleep(2000);
				handler.sendEmptyMessage(88);
				handler.obtainMessage(11, new Object()).sendToTarget();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void start(View v){
		MyThread myThread = new MyThread();
		myThread.start();
		
	}
	public void start2(View v){
		
		handler2.sendEmptyMessage(333);
	}
}
