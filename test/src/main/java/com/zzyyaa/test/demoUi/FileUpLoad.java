package com.zzyyaa.test.demoUi;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import org.apache.commons.fileupload.FileItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestParam;

import com.zzyyaa.test.Utils.CommonUtils;
import com.zzyyaa.test.Utils.UploadUtil;
import com.zzyyaa.test.entity.FileUploadEntity;

@Controller
@Path(value = "/demo")
public class FileUpLoad {
	
	private final static Logger logger = LoggerFactory.getLogger(FileUpLoad.class);
	
	@Value("${upload.file}")
	private String path;
	//包含浏览器请求的所有信息
	@Context
	protected UriInfo uriInfo;
	
	@Context
	private HttpServletRequest request;
	
	@Context
	private HttpServletResponse response;
	
	/**
	 * 上传文件接口
	 * 请求和其他方法一样，唯一的区别在于请求的参数中，文件和参数混合在一起，不好解析，需要借助Apache提供的FileUpload解析
	 * */
	@POST
	@Path(value = "/uploadFile")
	@Consumes("multipart/form-data")
	@Produces({"application/json;charset=utf-8"})
	public boolean	uploadFile(){
		boolean b = true;
		FileUploadEntity entity = UploadUtil.transFileEntity(request);
		List<FileItem> fileItems = entity.getFile();
		MultiValueMap<String, String> valueMap = entity.getParameters();
		BufferedOutputStream bf = null;
		FileOutputStream outputStream = null;
		try {
			for (FileItem fileItem : fileItems) {
				if (fileItem.getName() != null && !"".equals(fileItem.getName())) {
					InputStream inputStream = fileItem.getInputStream();
					String filePath = path + File.separator + CommonUtils.getNowDate(null);
					String fileAllPath = path + File.separator + CommonUtils.getNowDate(null) + File.separator + fileItem.getName();
					File file = new File(filePath);
					if (!file.exists()) {
						file.mkdirs();
					}
					outputStream = new FileOutputStream(new File(fileAllPath));
					bf = new BufferedOutputStream(outputStream);
					byte[] bs = new byte[1024];
					int hasRead = 0;
					while ((hasRead = inputStream.read(bs)) != -1) {
						bf.write(bs,0,hasRead);
					}
					bf.flush();
					bf.close();
					outputStream.flush();
					outputStream.close();
				}
			}
			for (String item : valueMap.keySet()) {
				System.out.println(item + "=" + valueMap.get(item));
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.debug("write file error");
			b = false;
		}finally {
			try {
				outputStream.flush();
				outputStream.close();
				bf.flush();
				bf.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				b = false;
			}
		}
		return b;
	}
	/**
	 * 文件下载接口
	 * get接口，文件的二进制流写入浏览器的response，设置response的contenttype
	 * request和response可以通过注解@context注入,也可直接在方法中注解获取
	 * */
	@GET
	@Path(value = "/downloadFile/{filePath}")
	public String downloadFile(@PathParam ("filePath") String filePath/*,@Context HttpServletResponse response*/){
		String result = null;
		MultivaluedMap<String, String> map = uriInfo.getQueryParameters();//可以获取浏览器路径的参数
		System.out.println(com.zzyyaa.test.entity.ServletEntity.getEntity());//仅为测试从servlet接收的httpservletrequest是否成功
		File file = new File("E:/file/2017-11-03/2222.jpg");//此处可根据get请求的参数查找文件路径，一般传入ID，从数据库取路径，此处仅为测试
		if (!file.exists()) 
			return null;
		response.setContentType("application/force-download");//设置为强行下载文件
		response.addHeader("Content-Disposition",
                "attachment;fileName=" +  file.getName());// 设置响应头、文件名
		InputStream in = null;
		BufferedInputStream bufferedInputStream = null;//缓冲流提高效率，减少磁盘操作
		OutputStream out = null;
		try {
			in = new FileInputStream(file);
			bufferedInputStream = new BufferedInputStream(in);
			byte[] bs = new byte[1024];
			out = response.getOutputStream();
			int hasRead = 0;
			while ((hasRead = bufferedInputStream.read(bs))!=-1) {
				out.write(bs, 0, hasRead);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (in!=null) {
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (bufferedInputStream!=null) {
				try {
					bufferedInputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return result;
	}
}
