package com.zzyyaa.test.Utils;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.zzyyaa.test.entity.FileUploadEntity;

public class UploadUtil {
	private static final Logger logger = LoggerFactory.getLogger(UploadUtil.class);

	/**
	 * 文件上传，把请求request转换为两部分，文件和参数
	 */
	public static FileUploadEntity transFileEntity(HttpServletRequest request) {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		FileUploadEntity fileUploadEntity = new FileUploadEntity();
		List<FileItem> fileItems = new LinkedList<>();
		MultiValueMap<String, String> valueMap = new LinkedMultiValueMap<>();
		if (!ServletFileUpload.isMultipartContent(request))
			return fileUploadEntity;
		try {
			Map<String, List<FileItem>> map = upload.parseParameterMap(request);
			for (Map.Entry<String, List<FileItem>> item : map.entrySet()) {
				List<String> paramList = new LinkedList<>();
				for (FileItem fileItem : item.getValue()) {
					if (fileItem.isFormField()) {
						paramList.add(fileItem.getString());
						valueMap.put(item.getKey(), paramList);
					} else {
						fileItems.add(fileItem);
					}
				}
			}
			fileUploadEntity.setFile(fileItems);
			fileUploadEntity.setParameters(valueMap);
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.debug("request translate fileEntity File");
		}
		return fileUploadEntity;
	}
}
