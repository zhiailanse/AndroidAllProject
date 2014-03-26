package com.zmm.allproject.four_big_component.simpleUI;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.ActionMode.Callback;
import com.actionbarsherlock.view.MenuItem.OnMenuItemClickListener;
import com.actionbarsherlock.view.ActionMode;
import com.actionbarsherlock.view.SubMenu;
import com.zmm.allproject.R;

public class ForUI extends SherlockActivity {

	boolean dark = true;
	Chronometer chro = null;
	Button bttn = null;
	PopupWindow pop = null;
	TextView tv = null;
	TextView tvIds = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setDebug(false);

		// transition theme
		if (getIntent() != null) {
			boolean b = getIntent().getBooleanExtra("dark", true);
			dark = b;
		}
		if (!dark) {
//			this.setTheme(android.R.style.Theme_DeviceDefault_Light);
			this.setTheme(R.style.style_my_edittext_field);
		}

		setContentView(R.layout.forui_layout);

		chro = (Chronometer) findViewById(R.id.chronometer1);
		registerForContextMenu(chro);
		bttn = (Button) findViewById(R.id.buttonPopup);

		LinearLayout layout = new LinearLayout(this);
		layout.setBackgroundColor(Color.GRAY);
		TextView tv = new TextView(this);
		tv.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
		tv.setText("I'm a pop -----------------------------!");
		tv.setTextColor(Color.WHITE);
		// layout.addView(tv);
		pop = new PopupWindow(getApplicationContext());
		pop.setContentView(tv);
		pop.setWidth(220);
		pop.setHeight(220);
		bttn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// showPopUp(bttn);
				if (!pop.isShowing()) {
					pop.showAsDropDown(bttn);
				} else {
					pop.dismiss();
				}
			}
		});

		tvIds = (TextView) findViewById(R.id.ids_myId_textview1);
		tvIds.setText("My id is from ids.xml! ha....");
	}

	public void buttonActionMode(View v) {
		ForUI.this.startActionMode(new Callback() {

			@Override
			public boolean onPrepareActionMode(ActionMode arg0,
					com.actionbarsherlock.view.Menu arg1) {
				return false;
			}

			@Override
			public void onDestroyActionMode(ActionMode arg0) {

			}

			@Override
			public boolean onCreateActionMode(ActionMode arg0,
					com.actionbarsherlock.view.Menu arg1) {
				com.actionbarsherlock.view.MenuInflater inflater = arg0
						.getMenuInflater();
				inflater.inflate(R.menu.context_menu, arg1);
				return true;
			}

			@Override
			public boolean onActionItemClicked(ActionMode arg0,
					com.actionbarsherlock.view.MenuItem arg1) {
				return false;
			}
		});
	}

	private void showPopUp(View v) {
		LinearLayout layout = new LinearLayout(this);
		layout.setBackgroundColor(Color.GRAY);
		TextView tv = new TextView(this);
		tv.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
		tv.setText("I'm a pop -----------------------------!");
		tv.setTextColor(Color.WHITE);
		layout.addView(tv);

		pop = new PopupWindow(layout, 120, 120);

		pop.setFocusable(true);
		pop.setOutsideTouchable(true);
		// pop.setBackgroundDrawable(new BitmapDrawable());

		int[] location = new int[2];
		v.getLocationOnScreen(location);

		// pop.showAtLocation(v, Gravity.NO_GRAVITY, location[0],
		// location[1]-pop.getHeight());
		pop.showAsDropDown(bttn);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		if (v.getId() == chro.getId()) {
			getMenuInflater().inflate(R.menu.context_menu, menu);
			menu.setHeaderIcon(android.R.drawable.ic_media_play)
					.setHeaderTitle("Timer controls");
		}
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.start_timer:
			chro.start();
			break;
		case R.id.stop_timer:
			chro.stop();
			break;
		case R.id.reset_timer:
			chro.setBase(SystemClock.elapsedRealtime());
			break;
		default:
			break;
		}
		return super.onContextItemSelected(item);
	}

	void setBackgroundColor() {
		this.setTheme(android.R.style.Theme_DeviceDefault_Light);
	}

	@Override
	public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu) {

		OnMenuItemClickListener onMenuItemClickListener = new OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(
					com.actionbarsherlock.view.MenuItem item) {
				switch (item.getItemId()) {
				case 0:
					Toast.makeText(ForUI.this, "item 1 onclck",
							Toast.LENGTH_SHORT).show();
					return true;
				case 222:
					Toast.makeText(ForUI.this, "item 2 onclck",
							Toast.LENGTH_SHORT).show();
					break;
				default:
					break;
				}
				return false;
			}
		};

		menu.add("Toast...")
				.setIcon(R.drawable.icon)
				.setOnMenuItemClickListener(onMenuItemClickListener)
				.setShowAsAction(
						com.actionbarsherlock.view.MenuItem.SHOW_AS_ACTION_ALWAYS);

		menu.add("jumpToAnotherActivity")
				.setIntent(new Intent(ForUI.this, ForUI.class))
				.setShowAsAction(
						com.actionbarsherlock.view.MenuItem.SHOW_AS_ACTION_ALWAYS);

		SubMenu style_choice = menu.addSubMenu("style_choice").setIcon(
				R.drawable.icon);
		style_choice.add(1, 111, 1, "light").setCheckable(true)
				.setChecked(!dark);
		style_choice.add(1, 222, 2, "dark").setCheckable(true).setChecked(dark);

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(
			com.actionbarsherlock.view.MenuItem item) {
		if (item.getItemId() == 111) {
			System.out.println("111 onclick");
			item.setChecked(true);
			setBackgroundColor();
			finish();
			Intent intent = new Intent(ForUI.this, ForUI.class);
			intent.putExtra("dark", false);
			ForUI.this.startActivity(intent);

		}
		if (item.getItemId() == 222) {
			System.out.println("222 onclick");
			item.setChecked(true);
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onOptionsMenuClosed(Menu menu) {
		super.onOptionsMenuClosed(menu);
		System.out.println("ForUI.onOptionsMenuClosed()");
	}
}
