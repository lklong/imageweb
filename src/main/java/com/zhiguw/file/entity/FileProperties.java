/**
 * 
 */
package com.zhiguw.file.entity;

import java.util.Arrays;
import java.util.List;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Value;

/**
 * @ClassName: FileProperties.java
 * @Author: liukailong
 * @Description:文件属性
 * 
 */
@Named
public class FileProperties {

	/** 文件最大值 */
	@Value("${file.max.size}")
	private String maxSize;

	/** 文件存放路径 */
	@Value("${file.path.real}")
	private String descPath;

	/** 文件服务器地址 */
	@Value("${file.path.host}")
	private String host;

	/** 文件后缀名 */
	@Value("${file.suffix}")
	private List<String> fileSuffix;

	public String getDescPath() {
		return descPath;
	}

	public void setDescPath(String descPath) {
		this.descPath = descPath;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public List<String> getFileSuffix() {
		return Arrays.asList(fileSuffix.get(0).split(","));
	}

	public void setFileSuffix(List<String> fileSuffix) {
		this.fileSuffix = fileSuffix;
	}

	public void setMaxSize(String maxSize) {
		this.maxSize = maxSize;
	}

	public String getMaxSize() {
		return maxSize;
	}

}
