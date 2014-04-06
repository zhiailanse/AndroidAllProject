package com.zmm.androidallproject.widgets;

import com.zmm.androidallproject.R;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class WidgetsActivity1 extends Activity{

	Gallery gallery;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.widgets_activity);
		gallery = (Gallery) findViewById(R.id.galleryWidgets1);
		
		gallery.setAdapter(new ImageAdapter(getApplicationContext()));
	}
	class ImageAdapter extends BaseAdapter{
		Context context;
		private final int mGalleryItemBackground ;
		public ImageAdapter(Context context) {
			super();
			this.context = context;
			//下面这个东西不设置图片就会重叠，no reason!
			TypedArray a = obtainStyledAttributes(R.styleable.default_gallery);
			mGalleryItemBackground = a.getResourceId(R.styleable.default_gallery_android_galleryItemBackground, 0);
			a.recycle();
		}

		private Integer[] mImageIds = {
				R.drawable.abs__ab_bottom_solid_dark_holo,
				R.drawable.abs__ab_bottom_solid_inverse_holo,
				R.drawable.abs__ab_bottom_transparent_light_holo,
				R.drawable.ic_icon,
				R.drawable.ic_launcher,
				R.drawable.abs__ab_bottom_solid_dark_holo,
				R.drawable.abs__ab_bottom_transparent_light_holo,
				R.drawable.ic_icon,
				R.drawable.ic_launcher
		};
		@Override
		public int getCount() {
			return mImageIds.length;
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView imageView = new ImageView(context);
			imageView.setBackgroundResource(mGalleryItemBackground);
			imageView.setImageResource(mImageIds[position]);
			imageView.setLayoutParams(new Gallery.LayoutParams(200, 200));
			imageView.setScaleType(ImageView.ScaleType.FIT_XY);
			return imageView;
		}
		
	}
}
