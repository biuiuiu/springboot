package com.zya.zone;

public class TestInterfaceAndAbstract {
	public static void main(String[] args) {
		new TestAbstractOne().setAge(35);
		new TestAbstractOne().printAge();
		new TestAbstractTwo().printAge();
	}
}
