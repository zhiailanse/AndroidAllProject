package com.zmm.androidallproject.four_big_component.DBandCP;

import com.zmm.androidallproject.four_big_component.DBandCP.PetTrackerDatabase.PetType;
import com.zmm.androidallproject.four_big_component.DBandCP.PetTrackerDatabase.Pets;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class PetTrackerDatabaseHelper extends SQLiteOpenHelper {
	
	private static final String Database_Name = "pet_tracker.db";
	private static final int Database_Version = 1;

	public PetTrackerDatabaseHelper(Context context) {
		super(context, Database_Name, null, Database_Version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table " + PetType.PetType_Table_Name + "(" + 
				PetType._ID + " integer primary key autoincrement," + PetType.Pet_Type_Name + " text "+");");
		
		db.execSQL("create table " + Pets.Pets_Table_Name +"(" 
				+ Pets._ID +" integer primary key autoincrement, " 
				+ Pets.Pet_Name+" text, "
				+ Pets.Pet_Type_Id + " integer "
				+ ");");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("drop table if exists " + Database_Name);
		onCreate(db);
		System.out.println("PetTrackerDatabaseHelper.onUpgrade()");
	}

}
