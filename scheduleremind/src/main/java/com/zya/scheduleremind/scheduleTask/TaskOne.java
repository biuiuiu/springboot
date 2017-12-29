package com.zya.scheduleremind.scheduleTask;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TaskOne implements ApplicationRunner{
	
	@Override
	public void run(ApplicationArguments arg0) throws Exception {
		runschedule("Hello,Baby");
	}
	
	
	@Scheduled(cron = "0 30 10,15 * * ? ")//每天10.30和15.30运行
	public void taskOne() {
		runschedule(null);
	}
	
	@Scheduled(cron = "0 0 9,14,19 * * ? ")//每天9.00和14.00和19.00运行
	public void taskTwo() {
		runschedule(null);
	}
	
	@Scheduled(cron = "0 25 11,17 * * ? ")//每天11.25和17.25运行
	public void taskThree() {
		runschedule(null);
	}
	@Scheduled(cron = "0 55 20 * * ? ")//每天20.55运行
	public void taskFour() {
		runschedule(null);
	}
//	@Scheduled(cron = "0/30 * * * * ? ")//测试用，每30s执行一次
//	public void taskTest() {
//		runschedule(null);
//	}
	private void runschedule(String ss){
		System.setProperty("java.awt.headless", "false"); 
		if (ss!=null) {
			ShowTable.showTable2(ss);
		}else {
			String one = TimeUtils.calcOutJobTime();//第一句
			String two = TimeUtils.getSentance();//第二句
			if (!"".equals(one) && !"".equals(two)) {
				String statement = "<html>" + one + "<br>" + two + "</html>";
				ShowTable.showTable2(statement);
			}
		}
	}


	
}
