package com.zmm.allproject.aidl;

import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable{
	private String bookName;
	private int bookPrice;
	
	public Book(){
		
	}
	
	public Book(Parcel parcel) {
		super();
		this.bookName = parcel.readString();
		this.bookPrice = parcel.readInt();
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public int getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(int bookPrice) {
		this.bookPrice = bookPrice;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(bookName);
		dest.writeInt(bookPrice);
	}
	
	public static final Parcelable.Creator<Book> CREATOR = new Creator<Book>() {

		@Override
		public Book createFromParcel(Parcel source) {
			return new Book(source);
		}

		@Override
		public Book[] newArray(int size) {
			return new Book[size];
		}
	};

	@Override
	public String toString() {
		return "Book [bookName=" + bookName + ", bookPrice=" + bookPrice + "]";
	}
	
}
