package com.zmm.androidallproject.four_big_component;

import java.util.Date;
import java.util.Locale;

import com.zmm.androidallproject.R;
import com.zmm.androidallproject.four_big_component.DBandCP.GalleryAsyncActivity;
import com.zmm.androidallproject.four_big_component.DBandCP.PetTrackerEntryActivity;
import com.zmm.androidallproject.mainUI.ListFragmentForConsole;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;

public class Fragment4CP extends ListFragmentForConsole {
	
	public final static String Create_Author_Table = "create table if not exists tbl_authors(id integer primary key autoincrement," +
			"firstname text," +
			"lastname text)";
	
	public static final String Create_Books_Table = "create table if not exists tbl_books(id integer primary key autoincrement," +
			"title text," +
			"dateadded date," +
			"authorid integer not null constraint authorid references tbl_authors(id) on delete cascade)";
	
	private static final String Create_Trigger_Add = "create trigger if not exists fk_insert_book before insert on tbl_books " +
			"for each row " +
			"begin " +
			"select raise(rollback,'插入books表中的authorid必须在author表中存在') where (" +
			"select id from tbl_authors where id = new.authorid) is null; " +
			"end;";
	public static String Author_Table_Name = "tbl_authors";
	public static String Books_Table_Name = "tbl_books";
	
	SQLiteDatabase mDatabase;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		createDB();
		insertSomeValue();
	}
	
	public void insertSomeValue(){
		ContentValues values = new ContentValues();
		values.put("firstname", "zhang");
		values.put("lastname", "mingming");
		long newAuthorId = mDatabase.insert(Author_Table_Name, null, values);
		
		ContentValues values2 = new ContentValues();
		values2.put("title", "AndroidallProject");
		values2.put("dateadded", new Date().toString());
//		values2.put("authorid", newAuthorId);
		values2.put("authorid", 444);
		
		try {
			mDatabase.insertOrThrow(Books_Table_Name, null, values2);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println(e.toString());
		}
	}
	
	public void createDB(){
		mDatabase = getActivity().openOrCreateDatabase("allProject.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
		mDatabase.setLocale(Locale.getDefault());
		mDatabase.setLockingEnabled(true);
		mDatabase.setVersion(1);
		
		mDatabase.execSQL(Create_Author_Table);
		mDatabase.execSQL(Create_Books_Table);
		mDatabase.execSQL(Create_Trigger_Add);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.activity_contain_fragment, null);
	}

	String[] objects = { "toPetsActivity" ,"toAsyncActivity"};

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		getListView()
				.setAdapter(
						new ArrayAdapter<String>(getActivity(),
								android.R.layout.simple_list_item_activated_1,
								objects));

		getListView().setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				switch (arg2) {
				case 0:
					Intent intent = new Intent(getActivity(),PetTrackerEntryActivity.class);
					getActivity().startActivity(intent);
					break;
				case 1:
					Intent intent1 = new Intent(getActivity(),GalleryAsyncActivity.class);
					getActivity().startActivity(intent1);
					break;
				default:
					break;
				}

			}
		});
	}

}
