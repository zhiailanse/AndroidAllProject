package com.zmm.allproject.four_big_component.activity_lifecycle;

import com.zmm.allproject.R;
import com.zmm.allproject.R.xml;
import com.zmm.allproject.mainUI.AllApplication;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.widget.Toast;

public class MyPreferenceActivity extends PreferenceActivity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		addPreferencesFromResource(R.xml.preferences);
	}
	
	@Override
	@Deprecated
	public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen,
			Preference preference) {
		
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
		prefs.registerOnSharedPreferenceChangeListener(new OnSharedPreferenceChangeListener() {
			
			@Override
			public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
					String key) {
				if(key.equals("CheckBox1")){
					AllApplication.allDebug(MyPreferenceActivity.this, sharedPreferences.getBoolean("CheckBox1", false)+" ");
				}
			}
		});
		
		String key = preference.getKey().toString();
		if(key.equals("CheckBox1")){
			AllApplication.allDebug(MyPreferenceActivity.this, "CheckBox1.onClick");
		}
		
		
//		String value = prefs.getString("CheckBox1", "unset");
//		Toast.makeText(MyPreferenceActivity.this, "value = " + value, 1).show();
		
		return super.onPreferenceTreeClick(preferenceScreen, preference);
	}
	
	
}
