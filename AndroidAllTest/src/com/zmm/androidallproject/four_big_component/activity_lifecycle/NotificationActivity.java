package com.zmm.androidallproject.four_big_component.activity_lifecycle;

import com.zmm.androidallproject.R;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.RemoteViews;
/**
 * 对于ID相同的通知，再次更新时Ticker并不会再次更新，所以要通过更新Number来提示更新的次数,而number在Android3.0以上是在下拉菜单里，通知条的右侧下方显示的
 * ，11版本之前是在通知栏里显示的（未测试）
 * 
 * @author zhang
 *
 */
public class NotificationActivity extends Activity{
	NotificationManager notifier;
	Notification notification = new Notification(R.drawable.icon, "hello world!!", System.currentTimeMillis());
	Intent toLaunch = new Intent(NotificationActivity.this, NotificationActivity.class);
	int notificationId = 0;
	Notification notifyNew;
	RemoteViews remote;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.notification_activity);
		initRemoteView();
		
		notifier = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		
		notify1();
		notifyNew();
	}
	
	public void initRemoteView(){
		remote = new RemoteViews(getPackageName(), R.layout.remote_view);
		Intent remoteIntent = new Intent("PendingBroadcastReceiver");
		remote.setOnClickPendingIntent(R.id.buttonPre,PendingIntent.getBroadcast(
				NotificationActivity.this, 
				0, 
				remoteIntent, 
				0));
	}
	
	public void onclick1(View v){
		notification.number += 1;
		notificationId++;
		notify1();
	}
	
	public void onclick2(View v){
		notifyNew.tickerText = "1111111111";
		notifyNew();
	}
	
	public void onclick3(View v){
		notifier.cancel(0);
	}
	
	public void onclick4(View v){
		notifier.cancelAll();
	}
	
	public void notify1(){
		
		notification.tickerText = "hello world!!!";//滚动显示的文字
		notification.flags = Notification.FLAG_AUTO_CANCEL;//以前的版本要在这里设置点击后通知消失的操作，而11版本后则有专门的方法来设置
		PendingIntent intentBack = PendingIntent.getActivity(
				NotificationActivity.this, 
				0, 
				toLaunch, 
				0);
		notification.setLatestEventInfo(
				NotificationActivity.this, 
				"contentTitle", //拉下来后看到的标题
				"contentText", //拉下来后看到的消息内容区
				intentBack);//点击后的动作
		
		//
		notification.audioStreamType = AudioManager.STREAM_NOTIFICATION;
//		notification.sound = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE+"://com.zmm.androidallproject/" + R.raw.fallbackring);
		notification.contentView = remote;
		
//		notification.flags = Notification.FLAG_INSISTENT;//不停的
		notifier.notify(notificationId, notification);//第一个参数为id，如果id相同则更新，不会重新创建
	}
	
	public void notifyNew(){
		
		notifyNew = new Notification.Builder(this)
			.setWhen(System.currentTimeMillis())
			.setTicker("hello zhang!!")
			.setNumber(48)
			.setAutoCancel(true)
			.setContentTitle("HI there!")
			.setSmallIcon(R.drawable.ic_icon)
			.setContentIntent(
					PendingIntent.getActivity(NotificationActivity.this, 1, toLaunch, 1)
					)
			.setDeleteIntent(//设置删除一个notification后的操作，可以打开一个Activity，BC,Service...
					PendingIntent.getBroadcast(NotificationActivity.this, 0, new Intent(""), PendingIntent.FLAG_CANCEL_CURRENT)
					)
			.getNotification()
			;
		notifyNew.contentView = remote;
//		notifyNew.vibrate = new long[]{0,200,200,600,600,900,900};// 开始震动200，暂停200，再震动600，暂停600
//		notifyNew.defaults = Notification.DEFAULT_VIBRATE | Notification.DEFAULT_SOUND;
		notifier.notify(999, notifyNew);
	}

}
