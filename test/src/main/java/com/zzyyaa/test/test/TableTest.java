package com.zzyyaa.test.test;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class TableTest {
	public static void main(String[] args) {
		List<Date> dateList = new ArrayList<>();
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 9);
		dateList.add(calendar.getTime());//早餐，开始上班
		
		calendar.set(Calendar.HOUR, 10);
		dateList.add(calendar.getTime());//工作1小时
		
		calendar.set(Calendar.HOUR, 11);
		calendar.set(Calendar.MINUTE, 25);
		dateList.add(calendar.getTime());//中午下班，午饭
		
		calendar.set(Calendar.HOUR, 13);
		calendar.set(Calendar.MINUTE, 40);
		dateList.add(calendar.getTime());//午觉结束，上班
		
		calendar.set(Calendar.HOUR, 15);
		calendar.set(Calendar.MINUTE, 30);
		dateList.add(calendar.getTime());//下午3.30
		
		calendar.set(Calendar.HOUR, 17);
		calendar.set(Calendar.MINUTE, 25);
		dateList.add(calendar.getTime());//下班
		showTable(dateList);
	}
	
	private static void showTable(List<Date> dates){
		Date dateNow = new Date();//当前时间
		int index = dates.indexOf(dateNow);
		System.out.println(index);
		String stateMent = "下班啦啦啦~";
		String[] strings = new String[]{"工作了很久了，要不要休息一下？","很棒哦，继续加油"};
		String randomString = strings[new Random().nextInt(5)%strings.length];
		switch (index) {
		case 0:
			stateMent = "";
			break;
		case 1:
			stateMent = randomString;
			break;
		case 2:
			stateMent = "";
			break;
		case 3:
			stateMent = "";
			break;
		case 4:
			stateMent = randomString;
			break;
		case 5:
			stateMent = "下班啦啦啦~";
			break;
		default:
			break;
		}
		showTable2(stateMent);
	}
	private static void showTable2(String statement){
		JFrame frame = new JFrame();
		frame.setBounds(30, 30, 220, 80);//设置大小
		frame.setUndecorated(true);//隐藏弹窗的最小化最大化和关闭按钮
		frame.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				
			}
		});
		
		Panel panel = new Panel();
		JLabel label = new JLabel(statement);
		label.setFont(new Font("宋体", Font.PLAIN, 20));
		
		panel.add(label);
		
		frame.add(panel);
		frame.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		frame.setVisible(true);
		
		try {
			Thread.sleep(3000);
			System.exit(0);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
			System.exit(0);
		}
	}
}
