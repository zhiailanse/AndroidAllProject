package com.zmm.androidallproject.four_big_component.DBandCP;

import com.zmm.androidallproject.R;
import com.zmm.androidallproject.utils.ImageUtils;

import android.content.Context;
import android.content.CursorLoader;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;

/**
 * 
 * @author zhang
 *AsyncTask 有三个泛型参数，
 *第一个是doInBackground()的参数类型
 *第二个是onProgressUpdate()中的参数类型
 *第三个是onPostExecute()的参数类型
 *AsyncTask.execute("")传入的数组是传给doInBackground()的参数
 *doInBackground()返回的参数是传给onPostExecute()的参数(数组)
 *
 *
 *LoaderCallbacks<Cursor> 实现此接口会重写三个方法：
 *onCreateLoader() 
 *while getSupportLoaderManager().initLoader(0, null, this); invoke.the first arg is a unique id ,the second is a bundle,the last is the LoaderCallbacks
 *while invoke also after getSupportLoaderManager().restartLoader(0, null, GalleryAsyncActivity.this);
 *this method start a cursorLoader ,while done,the cursor return is onLoaderFinished();
 *
 *and the three method is also in the mainThread!
 *
 *onLoaderFinished()
 *onLoaderReset()
 */
public class GalleryAsyncActivity extends FragmentActivity implements LoaderCallbacks<Cursor>{
	
	MyTask myTask;
	ProgressBar p;
	Gallery gallery;
	ImageAdapter imageAdapter;
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.async_activity);
		p = (ProgressBar) findViewById(R.id.progressBar1);
		p.setMax(100); //the ProgressBar default max value is 100
		gallery = (Gallery) findViewById(R.id.gallery2);
		
		imageAdapter = new ImageAdapter(getApplicationContext(), null, imageUri);
		gallery.setAdapter(imageAdapter);
		
		getSupportLoaderManager().initLoader(0, null, this);
		
		
		myTask = new MyTask();
		myTask.execute("ss");
	}
	
	public void onBtnClick (View view){
		System.out.println("rr");
	}
	
	private Uri imageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
	class ImageAdapter extends CursorAdapter{
		
		ImageView imageView;
		private Uri baseUri;
		private ImageTask imageTask;
		int colIndexId;
		private final int mGalleryItemBackground;
		
		public ImageAdapter(Context context, Cursor c,Uri uri) {
			super(context, c, false);
			this.baseUri = uri;
			if(c != null){
				colIndexId = c.getColumnIndex(MediaStore.Images.Media._ID);
			}
			TypedArray a = obtainStyledAttributes(R.styleable.default_gallery);
			mGalleryItemBackground = a.getResourceId(R.styleable.default_gallery_android_galleryItemBackground, 0);
			System.out.println("++" + mGalleryItemBackground);
			a.recycle();
		}

		@Override
		public void bindView(View arg0, Context arg1, Cursor arg2) {
		}

		@Override
		public View newView(Context context, Cursor cursor, ViewGroup root) {
//			if(imageView == null){
//				imageView = new ImageView(context);
//			}
			ImageView imageView = new ImageView(context);
			long id = cursor.getLong(colIndexId);
			System.out.println("==="+colIndexId);
			Uri imUri = Uri.withAppendedPath(baseUri, String.valueOf(id));
			imageView.setBackgroundResource(mGalleryItemBackground);
//			imageView.setBackgroundResource(R.drawable.ic_icon);
			imageTask = new ImageTask(imageView, imUri);
			imageTask.execute("none");
			return imageView;
		}
		
		@Override
		public Cursor swapCursor(Cursor newCursor) {
			if(newCursor != null){
				colIndexId = newCursor.getColumnIndex(MediaStore.Images.Media._ID);
			}
			return super.swapCursor(newCursor);
		}
		
	}
	
	class ImageTask extends AsyncTask<String, Integer, Bitmap>{
		ImageView imageView;
		Uri uri;
		
		public ImageTask(ImageView imageView, Uri uri) {
			super();
			this.imageView = imageView;
			this.uri = uri;
		}

		@Override
		protected Bitmap doInBackground(String... params) {
			Bitmap bitmap = ImageUtils.getBitmapFromUriCompress(GalleryAsyncActivity.this, uri, 200, 200);
			return bitmap;
		}
		
		@Override
		protected void onPostExecute(Bitmap result) {
			super.onPostExecute(result);
			//if use a gallery
			imageView.setLayoutParams(new Gallery.LayoutParams(
					200, 
					200));
//			imageView.setLayoutParams(new AbsListView.LayoutParams(100, 100));//if use a gridView
			imageView.setScaleType(ImageView.ScaleType.FIT_XY);
			imageView.setImageBitmap(result);
			imageView.invalidate();
		}
		
	}
	
	class MyTask extends AsyncTask<String, Integer, String>{

		@Override
		protected String doInBackground(String... params) {
			System.out.println("GalleryAsyncActivity.MyTask.doInBackground()");
			for(int i = 0;i <= 100 ;i++){
				try {
					Thread.sleep(10);
					publishProgress(i);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			return null;
		}

		@Override
		protected void onPreExecute() {
			System.out.println("GalleryAsyncActivity.MyTask.onPreExecute()");
			super.onPreExecute();
		}
		
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			System.out.println("GalleryAsyncActivity.MyTask.onPostExecute()");
			
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			super.onProgressUpdate(values);
			p.setProgress(values[0]);
		}

	}

	@Override
	public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
		System.out.println("GalleryAsyncActivity.onCreateLoader()");
		String[] projection = new String[] {
				MediaStore.Images.ImageColumns._ID
		};
		
		android.support.v4.content.CursorLoader loader = new android.support.v4.content.CursorLoader
				(getApplicationContext()
						, imageUri
						, projection, null, null
						, MediaStore.Images.Media.DEFAULT_SORT_ORDER);
		
		return loader;
	}

	@Override
	public void onLoadFinished(Loader<Cursor> arg0, Cursor cursor) {
		System.out.println("GalleryAsyncActivity.onLoadFinished()");
		imageAdapter.swapCursor(cursor);
		System.out.println("=="+cursor.getCount());
	}

	@Override
	public void onLoaderReset(Loader<Cursor> arg0) {
		System.out.println("GalleryAsyncActivity.onLoaderReset()");
		imageAdapter.swapCursor(null);
	}
	
}
