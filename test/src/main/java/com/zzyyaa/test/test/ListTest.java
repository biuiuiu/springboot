package com.zzyyaa.test.test;

import java.util.ArrayList;
import java.util.List;

public class ListTest {
	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			list.add("第"+(i+1)+"个");
		}
		list.forEach(item->{
			System.out.print(item);
		});
		System.out.println();
//		for (String string : list) {
//			if (Integer.parseInt(string.substring(1,2))>5) {
//				list.remove(string);
//			}
//		}
//		List<String> copyList = new ArrayList<>();
//		for (int i = 0; i < list.size(); i++) {
//			if ((i+1)>5) {
//				copyList.add(list.get(i));
//			}
//		}
//		list.removeAll(copyList);
//		list.forEach(item->{
//			System.out.print(item);
//		});
		System.out.println();
		list.removeIf(item->(
				list.indexOf(item)+1 > 5
				));//java8以后提供的删除方法
		list.forEach(item->{
			System.out.print(item);
		});
	}
}
