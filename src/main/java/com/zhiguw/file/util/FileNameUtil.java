/**
 * 
 */
package com.zhiguw.file.util;

import java.util.UUID;

import org.apache.commons.io.FilenameUtils;

import com.zhiguw.file.constants.GraphicImageConstants;

/**
 * @ClassName: FileNameUtil.java
 * @Author: liukailong
 * @Description:文件命名工具类
 * @Date: 2015年4月7日
 * 
 */
public class FileNameUtil {

	public static String generateFileName(String specs, String originalName) {

		String uuid = UUID.randomUUID().toString();

		StringBuffer name = new StringBuffer();

		String suffix = FilenameUtils.getExtension(originalName);

		name.append(uuid).append("_").append(GraphicImageConstants.FILE_NAME_SUFFIX).append("_0").append(specs).append(".").append(suffix);

		return name.toString();

	}

}
