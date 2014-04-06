package com.zmm.androidallproject.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.net.Uri;
import android.provider.MediaStore;

public class ImageUtils {
	
	public static Bitmap imageZoom(Bitmap bitmap){
		//
		double maxSize = 200.00;//kb
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.JPEG, 80, baos);
		
		byte[] b = baos.toByteArray();
		//to kb
		double mid = b.length/1024;
		System.out.println("压缩前：" + mid/1024 + " M");
		System.out.println("压缩前2：" + bitmap.getByteCount()/1024/1024 + " M");
		//
		if(bitmap.getByteCount()/1024 > maxSize){
			//
			System.out.println("压缩前888：" + bitmap.getByteCount()/1024 + " KB");
			double i = mid/maxSize;
			bitmap = zoomImage(bitmap, 200, 200);
			System.out.println("压缩后888：" + bitmap.getByteCount()/1024 + " KB");
		}
		
//		if(mid > maxSize){
//			//
//			double i = mid/maxSize;
//			bitmap = zoomImage(bitmap, bitmap.getWidth()/Math.sqrt(i), bitmap.getHeight()/Math.sqrt(i));
//		}
		
		System.out.println("压缩后：" + bitmap.getByteCount()/1024 );
		return bitmap;
	}
	
	private static Bitmap zoomImage(Bitmap bgImage,double newWidth,double newHeight){
		float width = bgImage.getWidth();
		float height = bgImage.getHeight();
		
		Matrix matrix = new Matrix();
		float scaleWidth = (float) newWidth/width;
		float scaleHeight = (float) newHeight/height;
		matrix.postScale(scaleWidth, scaleHeight);
		Bitmap bitmap = Bitmap.createBitmap(bgImage, 0, 0, (int) width, (int) height, matrix, true);
		
		return bitmap;
	}
	
	public static Bitmap getBitmapFromUri(Uri uri,Context context){
		try {
			Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);
			return bitmap;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Bitmap getBitmapFromUriCompress(Context context,Uri uri,int newWidth,int newHeight){
		Bitmap bitmap = null;
		InputStream input = null;
		try {
			Options options = new Options();
			options.inJustDecodeBounds = true;
			
			input = context.getContentResolver().openInputStream(uri);
			
			bitmap = BitmapFactory.decodeStream(input, null, options);
			if(bitmap == null){
				System.out.println("这里bitmap还是空"+options.outWidth+"-"+options.outHeight);
				
			}
			int scal = 0;
			if(options.outWidth > newWidth && options.outHeight > newHeight){
				scal = options.outWidth > options.outHeight ? options.outWidth/newWidth : options.outHeight/newHeight;
			}else {
				scal = 1;
			}
			input = context.getContentResolver().openInputStream(uri);
			System.out.println("scale = " + scal);
			options.inSampleSize = scal;
			options.inJustDecodeBounds = false;
			bitmap = BitmapFactory.decodeStream(input, null, options);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("erro");
		}
		
		return bitmap;
	}
	
}
