package com.zya.zone;

public class TestMan implements TestInterface{

	@Override
	public boolean isHandsome(String name) {
		// TODO Auto-generated method stub
		if (name.equals(TestInterface.name)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isMan(String name) {
		// TODO Auto-generated method stub
		if (name.equals("zya")) {
			return true;
		}
		return false;
	}

}
