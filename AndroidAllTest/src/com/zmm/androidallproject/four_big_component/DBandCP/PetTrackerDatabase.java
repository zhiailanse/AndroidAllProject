package com.zmm.androidallproject.four_big_component.DBandCP;

import android.provider.BaseColumns;

public class PetTrackerDatabase {
	
	private PetTrackerDatabase(){
		
	}
	
	public static final class Pets implements BaseColumns{
		private Pets(){}
		
		public static final String Pets_Table_Name = "table_pets";
		public static final String Pet_Name = "pet_name";
		public static final String Pet_Type_Id = "pet_type_id";
		public static final String Default_Sort_Order = "pet_name ASC";
	}
	
	public static final class PetType implements BaseColumns{
		private PetType(){}
		
		public static final String PetType_Table_Name = "table_pettypes";
		public static final String Pet_Type_Name = "pet_type";
		public static final String Default_Sort_Order = "pet_type ASC";
	}

}
