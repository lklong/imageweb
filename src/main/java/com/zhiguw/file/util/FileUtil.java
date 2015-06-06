/**
 * @ClassName: FileUtil.java
 * @Author: liukailong
 * @Description: 
 * @Date: 2015年6月6日
 * 
 */
package com.zhiguw.file.util;

import java.io.File;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * @author Administrator
 *
 * @description 文件转换
 */
public class FileUtil {

	public static File multipartFile2File(MultipartFile file) {
		CommonsMultipartFile commonsMultipartFile = (CommonsMultipartFile) file;
		DiskFileItem fileItem = (DiskFileItem) commonsMultipartFile.getFileItem();

		File _file = fileItem.getStoreLocation();
		return _file;
	}

}
