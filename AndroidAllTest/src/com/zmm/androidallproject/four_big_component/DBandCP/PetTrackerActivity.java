package com.zmm.androidallproject.four_big_component.DBandCP;

import com.zmm.androidallproject.four_big_component.DBandCP.PetTrackerDatabase.PetType;
import com.zmm.androidallproject.four_big_component.DBandCP.PetTrackerDatabase.Pets;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

// Similar to PetTracker, although we are keeping persistent Cursor and Database instances around
public class PetTrackerActivity extends FragmentActivity {

	protected PetTrackerDatabaseHelper mDatabase = null; 
	protected Cursor mCursor = null;
	protected SQLiteDatabase mDB = null;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mDatabase = new PetTrackerDatabaseHelper(this.getApplicationContext());
		mDB = mDatabase.getWritableDatabase();
		
	}
	
	private void insertSome(){
		ContentValues contentValues = new ContentValues();
		contentValues.put(PetType.Pet_Type_Name, "藏獒");
		mDB.insert(PetType.PetType_Table_Name, null, contentValues);
		
		contentValues.clear();
		contentValues.put(Pets.Pet_Name, "xiaoZang");
		contentValues.put(Pets.Pet_Type_Id, 1);
		mDB.insert(Pets.Pets_Table_Name, null, contentValues);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		
		if(mDB != null)
		{
			mDB.close();
		}
		
		if(mDatabase != null)
		{
			mDatabase.close();
		}
	}
	
	

}