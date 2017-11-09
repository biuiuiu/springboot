package com.zzyyaa.test.demoUi;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduleTaskTest {
	/**
	 * 指定时间生成txt文件
	 * */
	@Scheduled(cron = "0 0 16 * * ?")//定时设置每天的16点执行任务,启动类上需要开启定时器注解@EnableScheduling
	public void timerRate() throws IOException {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String temp = sdf.format(new Date());
		File file = new File(this.getClass().getResource("").getPath()+File.separator+"1.txt");
		BufferedOutputStream bf = null;
		System.out.println(temp);
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			bf = new BufferedOutputStream(fileOutputStream);
			bf.write(temp.getBytes());
			bf.flush();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			bf.close();
		}
    }
}
