package com.zzyyaa.test.test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageTest {
	private static void copyImage(File file, String filePath) throws IOException {
		BufferedImage bufferedImage = ImageIO.read(file);
		
		File newFile = new File(filePath+File.separator+file.getName());
		
		ImageIO.write(bufferedImage, "jpg", newFile);
	}
	public static void main(String[] args) throws IOException {
		File file = new File("D:\\2.jpg");
		copyImage(file, "D:\\testfile");
		
	}
}
