
package com.zmm.androidallproject.four_big_component.DBandCP;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

public class MyProvider extends ContentProvider {
	public static final String MIME_DIR_PREFIX = "vnd.android.cursor.dir";
	public static final String MIME_ITEM_PREFIX = "vnd.android.cursor.item";
	public static final String MIME_ITEM = "vnd.msi.people";
	public static final String MIME_TYPE_SINGLE = MIME_ITEM_PREFIX+"/"+MIME_ITEM;
	public static final String MIME_TYPE_MULTIPLE = MIME_DIR_PREFIX+"/"+MIME_ITEM;
	public static final String AUTHORITY = "com.zmm.";

	@Override
	public boolean onCreate() {
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getType(Uri uri) {
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		return null;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		return 0;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		return 0;
	}

}
