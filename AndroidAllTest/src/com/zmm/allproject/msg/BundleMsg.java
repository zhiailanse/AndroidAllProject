package com.zmm.allproject.msg;

import android.os.Parcel;
import android.os.Parcelable;

public class BundleMsg implements Parcelable{

	Object source;
	String msg;
	
	public BundleMsg(Object source, String msg) {
		super();
		this.source = source;
		this.msg = msg;
	}
	public Object getSource() {
		return source;
	}
	public void setSource(Object source) {
		this.source = source;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		
	}
	
}
