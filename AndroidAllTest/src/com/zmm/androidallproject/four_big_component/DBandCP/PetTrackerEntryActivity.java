package com.zmm.androidallproject.four_big_component.DBandCP;

import java.lang.ref.SoftReference;
import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.database.sqlite.SQLiteQueryBuilder;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.zmm.androidallproject.R;
import com.zmm.androidallproject.four_big_component.DBandCP.PetTrackerDatabase.PetType;
import com.zmm.androidallproject.four_big_component.DBandCP.PetTrackerDatabase.Pets;
import com.zmm.androidallproject.utils.ImageUtils;

/**
 * imageLoader lifecycle
 * onCreateloader()
 * onLoadFinished()
 * adapter.swapCursor()
 * adapter.newView()
 * adapter.bindView()
 * 
 * .......new()...bind()...
 * @author zhang
 *
 */
public class PetTrackerEntryActivity extends PetTrackerActivity implements LoaderCallbacks<Cursor>{
	Gallery gallery = null;
	/** Called when the activity is first created. */
	
	HashMap<String, SoftReference<Bitmap>> cache = new HashMap<String, SoftReference<Bitmap>>();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.petentry);
		mGalleryAdapter = new ImageUriAdapter(getApplicationContext(), null, false, thumbnalUri);
	
		// Handle Save Button
		final Button savePet = (Button) findViewById(R.id.ButtonSave);
		savePet.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				final EditText petName = (EditText) findViewById(R.id.EditTextName);
				final EditText petType = (EditText) findViewById(R.id.EditTextSpecies);

				// Save new records
				mDB.beginTransaction();
				try {

					// check if species type exists already
					long rowId = 0;
					String strPetType = petType.getText().toString()
							.toLowerCase();

					// SQL Query
					SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
					queryBuilder.setTables(PetType.PetType_Table_Name);
					queryBuilder.appendWhere(PetType.Pet_Type_Name + "='"
							+ strPetType + "'");

					// run the query since it's all ready to go
					Cursor c = queryBuilder.query(mDB, null, null, null, null,
							null, null);

					if (c.getCount() == 0) {
						// add the new type to our list
						ContentValues typeRecordToAdd = new ContentValues();
						typeRecordToAdd.put(PetType.Pet_Type_Name, strPetType);
						rowId = mDB.insert(PetType.PetType_Table_Name,
								null, typeRecordToAdd);
						
						// Update autocomplete with new record
//						fillAutoCompleteFromDatabase();						
						
					} else {
						c.moveToFirst();
						rowId = c.getLong(c.getColumnIndex(PetType._ID));
					}

					c.close();

					// Always insert new pet records, even if the names clash
					ContentValues petRecordToAdd = new ContentValues();
					petRecordToAdd.put(Pets.Pet_Name, petName.getText()
							.toString());
					petRecordToAdd.put(Pets.Pet_Type_Id, rowId);
					mDB.insert(Pets.Pets_Table_Name, null,
							petRecordToAdd);

					c.close();
					mDB.setTransactionSuccessful();
				}catch(Exception e){
					System.out.println("++++++++++++="+e.getMessage());
				} finally {
					mDB.endTransaction();
					
				}

				// reset form
				petName.setText(null);
				petType.setText(null);
			}
		});

		// Handle Go to List button
		final Button gotoList = (Button) findViewById(R.id.ButtonShowPets);
		gotoList.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				// Go to other activity that displays pet list
				Intent intent = new Intent(PetTrackerEntryActivity.this, PetTrackerListActivity.class);
				startActivity(intent);
			}
		});
		
		setGalleryAdapter();
	}
	
//	private Uri thumbnalUri = MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI;
	private Uri thumbnalUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
	private final int GALLERY_CURSOR_LOADER_ID = 0x1001;
	private ImageUriAdapter mGalleryAdapter ;
	private ImageTask imageTask ;
	
	private void setGalleryAdapter(){
		gallery = (Gallery) findViewById(R.id.gallery1);
		gallery.setAdapter(mGalleryAdapter);
		
		Bundle args = new Bundle();
		args.putString("imageUri", args.toString());
//		getLoaderManager().initLoader(0x1001, args, this);
		getSupportLoaderManager().initLoader(GALLERY_CURSOR_LOADER_ID, args, this);
	}
	
	private class ImageTask extends AsyncTask<String, Integer, Bitmap>{
		ImageView imageView;
		Uri uri;
		public ImageTask(ImageView imageView,Uri uri) {
			this.uri = uri;
			this.imageView = imageView;
		}
		
		@Override
		protected Bitmap doInBackground(String... params) {
			Bitmap bitmap = ImageUtils.getBitmapFromUriCompress(getApplicationContext(), uri, 200, 200);
			return bitmap;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			super.onPostExecute(result);
			imageView.setLayoutParams(new Gallery.LayoutParams(
					200, 
					200));
			imageView.setImageBitmap(result);
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			super.onProgressUpdate(values);
		}
		
	}
	
	public class ImageUriAdapter extends android.support.v4.widget.CursorAdapter{
		private int colIndexMedisId;
		private final Uri baseUri;
		private final int mGalleryItemBackground;

		public ImageUriAdapter(Context context, Cursor c, boolean autoRequery,Uri baseUri) {
			super(context, c, autoRequery);
			if(c != null){
				colIndexMedisId = c.getColumnIndex(MediaStore.Images.Thumbnails._ID);
			}
			this.baseUri = baseUri;
			TypedArray a = obtainStyledAttributes(R.styleable.default_gallery);
			mGalleryItemBackground = a.getResourceId(R.styleable.default_gallery_android_galleryItemBackground, 0);
			a.recycle();
		}

		@Override
		public void bindView(View view, Context context, Cursor cursor) {
//			System.out
//					.println("PetTrackerEntryActivity.ImageUriAdapter.bindView()");
			
		}

		@Override
		public View newView(Context context, Cursor cursor, ViewGroup parent) {
			System.out
					.println("PetTrackerEntryActivity.ImageUriAdapter.newView()");
			ImageView imageView = new ImageView(context);
			long id = cursor.getLong(colIndexMedisId);
			
			Uri imUri = Uri.withAppendedPath(baseUri, String.valueOf(id));
			System.out.println("uri : "+imUri.toString());
			
//			Bitmap bitmap = null;
//			SoftReference<Bitmap> soft = cache.get(imUri.toString());
//			
//			if(soft != null){
//				bitmap = cache.get(imUri.toString().trim()).get();
//				if(bitmap != null){
//					
//					imageView.setImageBitmap(bitmap);
//					System.out.println("..........."+bitmap.getWidth());
//					imageView.setLayoutParams(new Gallery.LayoutParams(
//							200, 
//							200));
//					imageView.setTag(imUri);
//					imageView.setBackgroundResource(mGalleryItemBackground);
//					return imageView;
//				}else{
//					bitmap = ImageUtils.getBitmapFromUriCompress(context, imUri, 200, 200);
//					imageView.setImageBitmap(bitmap);
//					System.out.println("--------------");
//					
//					soft = new SoftReference<Bitmap>(bitmap);
//					cache.put(imUri.toString().trim(), soft);
////					bitmap = null;
//					return imageView;
//				}
//			}
//			
//			if(soft == null){
//				bitmap = ImageUtils.getBitmapFromUriCompress(context, imUri, 200, 200);
//				imageView.setImageBitmap(bitmap);
//				System.out.println(";;;;;;;;;;;");
//				
//				soft = new SoftReference<Bitmap>(bitmap);
//				cache.put(imUri.toString().trim(), soft);
//				bitmap = null;
//			}
//			
//			imageView.setLayoutParams(new Gallery.LayoutParams(
//					200, 
//					200));
//			imageView.setTag(imUri);
//			
//			imageView.setBackgroundResource(mGalleryItemBackground);
			imageView.setBackgroundResource(mGalleryItemBackground);
			imageTask = new ImageTask(imageView, imUri);
			imageTask.execute("111");
			return imageView;
		}
		
		@Override
		public Cursor swapCursor(Cursor newCursor) {
			System.out
					.println("PetTrackerEntryActivity.ImageUriAdapter.swapCursor()");
			if(newCursor != null){
				colIndexMedisId = newCursor.getColumnIndex(MediaStore.Images.Thumbnails._ID);
			}
			
			return super.swapCursor(newCursor);
		}
		
	}
	
	@Override
	public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
		System.out.println("PetTrackerEntryActivity.onLoadFinished()");
		switch(cursorLoader.getId()){
		case GALLERY_CURSOR_LOADER_ID:
			mGalleryAdapter.swapCursor(cursor);
			break;
		}
	}

	@Override
	public void onLoaderReset(Loader<Cursor> cursorLoader) {
		System.out.println("PetTrackerEntryActivity.onLoaderReset()");
		switch(cursorLoader.getId()){
		case GALLERY_CURSOR_LOADER_ID:
			mGalleryAdapter.swapCursor(null);
			break;
		}
	}

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle arg1) {
		System.out.println("PetTrackerEntryActivity.onCreateLoader()");
		switch(id){
		case GALLERY_CURSOR_LOADER_ID:
			String[] projection = new String[] {MediaStore.Images.Thumbnails._ID};
			
			CursorLoader loader = new CursorLoader(this, thumbnalUri, projection, 
					null, null, 
					MediaStore.Images.Media.DEFAULT_SORT_ORDER);
			return loader;
		}
		return null;
	}
	
}