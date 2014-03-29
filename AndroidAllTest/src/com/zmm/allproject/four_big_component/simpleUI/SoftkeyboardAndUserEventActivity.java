package com.zmm.allproject.four_big_component.simpleUI;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipData.Item;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.view.View.OnCreateContextMenuListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zmm.allproject.R;

public class SoftkeyboardAndUserEventActivity extends Activity {
	ClipboardManager clipboard = null;
	Button btnLongPress = null;
	Context context = SoftkeyboardAndUserEventActivity.this;
	EditText editTextFocus = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.softkeyboard_and_userevent);
		
		btnLongPress = (Button) findViewById(R.id.buttonLongPress);
		editTextFocus = (EditText) findViewById(R.id.editTextOnFocus);
		editTextFocus.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				Toast.makeText(context, "has focus = " + hasFocus, 0).show();
			}
		});
		
		btnLongPress.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {
			
			@Override
			public void onCreateContextMenu(ContextMenu menu, View v,
					ContextMenuInfo menuInfo) {
				Toast.makeText(context, "onCreateContextMenu", 0).show();
			}
		});
		btnLongPress.setOnFocusChangeListener(new OnFocusChangeListener() {
			/*
			 * button won`t need to obtain focus ,only EditText etc need to
			 */
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				Toast.makeText(context, "onFocusChange", 0).show();
			}
		});
		btnLongPress.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				Toast.makeText(context, "onLongClick", 0).show();
				return false;
			}
		});
		btnLongPress.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(context, "onClick", 0).show();
			}
		});
		btnLongPress.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				
				return false;
			}
		});
		
		clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
		ClipData clip = ClipData.newPlainText("kkk", "this is from clipCoard...");
		clipboard.setPrimaryClip(clip);
	}
	
	
	/**
      * 从Clip中取数据
      */
     private void get(){
         clipboard = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
         Item item = null;
         
        //无数据时直接返回
         if(!clipboard.hasPrimaryClip()){
             Toast.makeText(getApplicationContext(), "剪贴板中无数据", Toast.LENGTH_SHORT).show();
            return ;
         } 
        
        //如果是文本信息
         if (clipboard.getPrimaryClipDescription().hasMimeType(
                 ClipDescription.MIMETYPE_TEXT_PLAIN)) {
            ClipData cdText = clipboard.getPrimaryClip();
            item = cdText.getItemAt(0);
             //此处是TEXT文本信息
             if(item.getText() == null){
                 Toast.makeText(getApplicationContext(), "剪贴板中无内容", Toast.LENGTH_SHORT).show();
                 return ;
             }else{
                 Toast.makeText(getApplicationContext(), item.getText(), Toast.LENGTH_SHORT).show();
             }
 
         //如果是INTENT
         } else if (clipboard.getPrimaryClipDescription().hasMimeType(
                 ClipDescription.MIMETYPE_TEXT_INTENT)) {
             //此处是INTENT
             item = clipboard.getPrimaryClip().getItemAt(0);
             Intent intent = item.getIntent();
             startActivity(intent);
             //........
         
        //如果是URI
         } else if (clipboard.getPrimaryClipDescription().hasMimeType(
                 ClipDescription.MIMETYPE_TEXT_URILIST)) {
             //此处是URI内容www.2cto.com
            ContentResolver cr = getContentResolver();
             ClipData cdUri = clipboard.getPrimaryClip();
             item = cdUri.getItemAt(0);
             Uri uri = item.getUri();
             if(uri != null){
                 String mimeType = cr.getType(uri);
                 if (mimeType != null) {
                     if (mimeType.equals("some_uri")) {
                         Cursor pasteCursor = cr.query(uri, null, null, null, null);
                         if (pasteCursor != null) {
                             if (pasteCursor.moveToFirst()) {
                                  //此处对数据进行操作就可以了,前提是有权限
                             }
                        }
                         pasteCursor.close();
                     }
                  }
             }
         }
     }
}
