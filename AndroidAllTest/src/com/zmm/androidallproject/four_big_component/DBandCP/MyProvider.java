
package com.zmm.androidallproject.four_big_component.DBandCP;

import java.util.HashMap;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

public class MyProvider extends ContentProvider {
	public static final String MIME_DIR_PREFIX = "vnd.android.cursor.dir";
	public static final String MIME_ITEM_PREFIX = "vnd.android.cursor.item";
	public static final String MIME_ITEM = "vnd.msi.people";
	public static final String MIME_TYPE_SINGLE = MIME_ITEM_PREFIX+"/"+MIME_ITEM;
	public static final String MIME_TYPE_MULTIPLE = MIME_DIR_PREFIX+"/"+MIME_ITEM;
	public static final String AUTHORITY = "com.zmm.androidallproject.peopleProvider";
	public static final String PATH_SINGLE = "people/#";
	public static final String PATH_MULTIPLE = "people";
	public static final Uri content_URI = Uri.parse("content://"+AUTHORITY+"/"+PATH_MULTIPLE);
	
	public static final String DEFAULT_SORT_ORDER = "_id DESC";
	public static final String _ID = "_id";
	public static final String NAME = "name";
	public static final String PHONE = "phone";
	public static final String AGE = "age";
	
	public static final int PEOPLE = 1;
	public static final int PEOPLES = 2;
	
	private static UriMatcher URI_MATCHER;
	private static HashMap<String, String> PROJECTION_MAP;
	
	public static String DB_NAME = "peopledb";
	public static String DB_TABLE_NAME = "people";
	SQLiteDatabase db;
	DBOpenHelpter dbOpenHelpter;
	
	static{
		URI_MATCHER=new UriMatcher(UriMatcher.NO_MATCH);
		URI_MATCHER.addURI(AUTHORITY, PATH_MULTIPLE, PEOPLES);
		URI_MATCHER.addURI(AUTHORITY, PATH_SINGLE, PEOPLE);
		PROJECTION_MAP=new HashMap<String, String>();
		PROJECTION_MAP.put(_ID, "_id");
		PROJECTION_MAP.put(NAME,"name");
		PROJECTION_MAP.put(PHONE,"phone");
		PROJECTION_MAP.put(AGE,"age");
	}

	@Override
	public boolean onCreate() {
		dbOpenHelpter = new DBOpenHelpter(this.getContext(), DB_NAME, 2);
		db = dbOpenHelpter.getWritableDatabase();
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
		queryBuilder.setTables(DBInfo.DB_TABLE_NAME);
		queryBuilder.setProjectionMap(PROJECTION_MAP);
		switch(URI_MATCHER.match(uri)){
			case PEOPLES:
				break;
			case PEOPLE:
				queryBuilder.appendWhere("_id"+uri.getPathSegments().get(1));
				break;
			default:
				throw new IllegalArgumentException();
		}
		String orderBy = null;
		if(TextUtils.isEmpty(sortOrder)){
			orderBy = DEFAULT_SORT_ORDER;
		}else{
			orderBy = sortOrder;
		}
		Cursor c = queryBuilder.query(db, projection, selection, selectionArgs, null, null, orderBy);
		return c;
	}

	@Override
	public String getType(Uri uri) {
		switch(URI_MATCHER.match(uri)){
		case PEOPLES:
			return MIME_TYPE_MULTIPLE;
		case PEOPLE:
			return MIME_TYPE_SINGLE;
		default:
			throw new IllegalArgumentException("Unknow URI:"+uri);
		}
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		Log.d("moon", "blob");
		long rowId = 0l;
		if(URI_MATCHER.match(uri) != PEOPLES){
			throw new IllegalArgumentException("Unkonwn URI : " + uri);
		}
		rowId = db.insert(DBInfo.DB_TABLE_NAME, null, values);
		if(rowId > 0){
			Uri result = ContentUris.withAppendedId(content_URI, rowId);
			getContext().getContentResolver().notifyChange(result, null);
			return result;
		}else{
			throw new SQLException("Failed to insert row int " + uri);
		}
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		int count;
		switch(URI_MATCHER.match(uri)){
		case PEOPLES:
			count = db.delete(DBInfo.DB_TABLE_NAME, selection, selectionArgs);
			break;
		case PEOPLE:
			String segment = uri.getPathSegments().get(1);
			String where = "";
			if(!TextUtils.isEmpty(selection)){
				where = " and (" + selection + ")";
			}
			count = db.delete(DBInfo.DB_TABLE_NAME, "_id = " + segment + where, selectionArgs);
			break;
		default:
			throw new IllegalArgumentException("Unkonw URI : " + uri);
	}
		getContext().getContentResolver().notifyChange(uri, null);
		return count;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		int count = 0;
		switch(URI_MATCHER.match(uri)){
		case PEOPLES:
			count = db.update(DBInfo.DB_TABLE_NAME, values, selection, selectionArgs);
			break;
		case PEOPLE:
			String segment = uri.getPathSegments().get(1);
			String where = "";
			if(!TextUtils.isEmpty(selection)){
				where = "and("+selection+")";
			}
			count = db.update(DBInfo.DB_TABLE_NAME, values, "_id ="+segment+where, selectionArgs);
			break;
		default:
			throw new IllegalArgumentException();
	}
		getContext().getContentResolver().notifyChange(uri, null);
		return count;
	}
	
}
class DBOpenHelpter extends SQLiteOpenHelper{
	private static final String DB_CREATE = "create table "+
			DBInfo.DB_TABLE_NAME+"(_id integer primary key autoincrement,name blob not null,phone text,age integer);";//
	final static String tag = "zmm";

	public DBOpenHelpter(Context context, String name,
			 int version) {
		super(context, name, null, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		try {
			db.execSQL(DB_CREATE);
		} catch (Exception e) {
			e.printStackTrace();
			Log.d(tag, "",e);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("drop table if exists " + DBInfo.DB_TABLE_NAME);
		this.onCreate(db);
	}
}
class DBInfo{
	public static String DB_NAME = "peopledb";
	public static String DB_TABLE_NAME = "people";
}
