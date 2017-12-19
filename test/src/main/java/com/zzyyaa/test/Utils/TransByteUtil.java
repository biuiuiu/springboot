package com.zzyyaa.test.Utils;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TransByteUtil {
	/**
	 * 根据二进制数组和路径写入文件
	 * @param 文件路径
	 * @param byte[]
	 * @author biuiuiu
	 * */
	private static boolean transByte2File(byte[] bytes,String filePath,String fileName){
		if (bytes == null || filePath == null || fileName == null)
			return false;
		boolean b;
		
		File file = new File(filePath);
		if (!file.exists()) {
			file.mkdirs();
		}
		file = new File(filePath+File.separator+fileName);
		OutputStream out= null;
		InputStream in = null;
		BufferedOutputStream bfout = null;
		try {
			out = new FileOutputStream(file);
			bfout = new BufferedOutputStream(out);
			
			in = new ByteArrayInputStream(bytes);
			byte[] bs = new byte[1024];
			int hasRead = 0;
			while ((hasRead = in.read(bs))>0) {
				bfout.write(bs, 0, hasRead);
				}
			b = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			b = false;
		}finally {
			try {
				if (in!=null) {
					in.close();
				}
				if (bfout!=null) {
					bfout.close();
				}
				if (out!=null) {
					out.close();
				}
			} catch (IOException e) {
				System.out.println("Error:Stream Close Error!");
			}
		}
		return b;
	}
	/**
	 * 根据文件返回二进制数组
	 * @param 文件路径
	 * @return byte[]
	 * @author biuiuiu
	 * */
	private static byte[] getByteByFile(String filePath,String fileName){
		if (filePath == null || fileName == null) {
			return new byte[]{};
		}
		File file = new File(filePath+File.separator+fileName);
		if (!file.exists()) {
			System.out.println("Warn:File Not Found");
			return new byte[]{};
		}
		InputStream inputStream  = null;
		ByteArrayOutputStream out = null;
		try {
			inputStream = new FileInputStream(file);
			out = new ByteArrayOutputStream();
			byte[] bs = new byte[1024];
			int hasRead = 0;
			while ((hasRead = inputStream.read(bs))>0) {
				out.write(bs, 0, hasRead);
			}
			return out.toByteArray();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("Error:Error");
			return new byte[]{};
		}finally {
				try {
					if (inputStream!=null) {
						inputStream.close();
					}
					if (out != null) {
						out.close();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	}
	
	public static void main(String[] args) {
		transByte2File(getByteByFile("D:", "navicat111_premium_cs_x86_11.1.14.0.1453198735.exe"), "D:\\testfile", "2.exe");
		System.out.println(Integer.MAX_VALUE);
		System.out.println(Long.MAX_VALUE);
	}
}
