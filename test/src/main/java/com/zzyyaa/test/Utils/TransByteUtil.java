package com.zzyyaa.test.Utils;

import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

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
		BufferedWriter bufferedWriter = null;
		try {
			out = new FileOutputStream(file);
			bfout = new BufferedOutputStream(out);
			
			in = new ByteArrayInputStream(bytes);
			byte[] bs = new byte[1024];
			int hasRead = 0;
			while ((hasRead = in.read(bs))>0) {
				bfout.write(bs, 0, hasRead);
				}
//			bfout.write("\r\n何以解忧唯有杜康".getBytes("GB2312"));//在文件后加文字，适用于txt，编码默认是这个 并不是utf-8
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
				if (bufferedWriter!=null) {
					bufferedWriter.close();
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
	/**创建图片的缩略图*/
	private static void createThumbnail(String filename, int thumbWidth, int thumbHeight, String outFilename)
			throws InterruptedException, FileNotFoundException, IOException
			{
			// load image from filename
			Image image = Toolkit.getDefaultToolkit().getImage(filename);
			MediaTracker mediaTracker = new MediaTracker(new Container());
			mediaTracker.addImage(image, 0);
			mediaTracker.waitForID(0);
			// use this to test for errors at this point: System.out.println(mediaTracker.isErrorAny());

			// determine thumbnail size from WIDTH and HEIGHT
			double thumbRatio = (double)thumbWidth / (double)thumbHeight;
			int imageWidth = image.getWidth(null);
			int imageHeight = image.getHeight(null);
			double imageRatio = (double)imageWidth / (double)imageHeight;
			if (thumbRatio < imageRatio) {
			thumbHeight = (int)(thumbWidth / imageRatio);
			} else {
			thumbWidth = (int)(thumbHeight * imageRatio);
			}

			// draw original image to thumbnail image object and
			// scale it to the new size on-the-fly
			BufferedImage thumbImage = new BufferedImage(thumbWidth, thumbHeight, BufferedImage.TYPE_INT_RGB);
			Graphics2D graphics2D = thumbImage.createGraphics();
			graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			graphics2D.drawImage(image, 0, 0, thumbWidth, thumbHeight, null);

			// save thumbnail image to outFilename
			BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File("D:\\testfile"+File.separator+outFilename)));
			ImageIO.write(thumbImage, "jpg", out);
			out.close();
			}
	
	public static void main(String[] args) {
		transByte2File(getByteByFile("D:", "2.jpg"), "D:\\testfile", "test2.jpg");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println(methodName);
		try {
			createThumbnail("2.jpg", 500, 500, "2eee.jpg");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
