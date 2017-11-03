package com.zzyyaa.test.demoUi;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.jboss.resteasy.springmvc.RequestUtil;
import org.jboss.resteasy.springmvc.ResteasyRequestWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.zzyyaa.test.Utils.CommonUtils;
import com.zzyyaa.test.Utils.UploadUtil;
import com.zzyyaa.test.entity.FileUploadEntity;

@Controller
@Path(value = "/demo")
public class FileUpLoad {
	
	private final static Logger logger = LoggerFactory.getLogger(FileUpLoad.class);
	
	@Value("${upload.file}")
	private String path;
	
	@Context
	private HttpServletRequest request;
	
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
	
}
