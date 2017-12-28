package com.zya.scheduleremind.scheduleTask;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ShowTable {
	public static void showTable2(String statement){
		JFrame frame = new JFrame();
		frame.setBounds(30, 30, 300, 80);//设置大小
		frame.setUndecorated(true);//隐藏弹窗的最小化最大化和关闭按钮
		
		Toolkit kToolkit = Toolkit.getDefaultToolkit();//定义工具包
		Dimension screenSize = kToolkit.getScreenSize();//屏幕尺寸
		int width = screenSize.width;
		int height = screenSize.height;
		
		Insets screenInsets = Toolkit.getDefaultToolkit().getScreenInsets(frame.getGraphicsConfiguration());  
		int taskBarHeight = screenInsets.bottom;   //底部任务栏
		frame.setLocation(width - frame.getWidth(), height-taskBarHeight-frame.getHeight());//设置在屏幕右下角
		
		
		JPanel panel = new JPanel();
		JLabel label = new JLabel(statement);
		label.setFont(new Font("宋体", Font.PLAIN, 20));
		
		panel.add(label);
		frame.add(panel);
		frame.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		frame.setVisible(true);
		
		try {
			Thread.sleep(5000);
			frame.setVisible(false);//5s自动关闭
		} catch (InterruptedException e1) {
			e1.printStackTrace();
			System.exit(0);
		}
	}
}
