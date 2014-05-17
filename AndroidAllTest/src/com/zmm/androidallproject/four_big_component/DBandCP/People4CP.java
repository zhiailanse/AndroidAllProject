package com.zmm.androidallproject.four_big_component.DBandCP;

public class People4CP {
	private String name ;
	private String age;
	private String phone;
	private String _id;
	public People4CP(String name, String age, String phone, String _id) {
		super();
		this.name = name;
		this.age = age;
		this.phone = phone;
		this._id = _id;
	}
	public People4CP() {
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	@Override
	public String toString() {
		return "People4CP [name=" + name + ", age=" + age + ", phone=" + phone
				+ ", _id=" + _id + "]";
	}
}
