package com.zya.zone;

public class TestAbstractOne extends TestAbstract{
	
	public int setAge(int ii) {
		TestAbstract.age = ii;
		return TestAbstract.age;
	}
	@Override
	public void printAge() {
		System.out.println(TestAbstract.age);
	}
}
