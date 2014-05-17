package com.zmm.androidallproject.multiThread;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.zmm.androidallproject.R;

public class AsynctaskActivity extends Activity{
	Context context;
	ProgressBar progressBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.async_layout);
		context = getApplicationContext();
		progressBar = (ProgressBar)findViewById(R.id.progressBar1);
		
	}
	
	public void start(View v){
		MyAsynctask task = new MyAsynctask();
		task.execute("begin");
	}
	
	class MyAsynctask extends AsyncTask<String, Integer, String>{
		int progress = 0;
		@Override
		protected void onPreExecute() {
			progressBar.setProgress(20);
			super.onPreExecute();
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			super.onProgressUpdate(values);
			progressBar.setProgress(values[0]);
		}

		@Override
		protected String doInBackground(String... params) {
			while(progress < 100){
				progress += 1;
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				publishProgress(progress);
			}
			return "ok";
		}
		
	}
}
