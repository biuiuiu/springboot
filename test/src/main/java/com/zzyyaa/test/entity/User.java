package com.zzyyaa.test.entity;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.zzyyaa.test.customAnnotaion.MyFirstAnnotaion;

public class User {
	private long id;
	private String userName;
	private int userAge;
	private boolean userSex;
	@JSONField(format = "yy-MM-dd")
	private Date createDate;
	private Date updateDate;
	@MyFirstAnnotaion(type = "STATE")
	private String state;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getUserAge() {
		return userAge;
	}

	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}

	public boolean getUserSex() {
		return userSex;
	}

	public void setUserSex(boolean userSex) {
		this.userSex = userSex;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

}
