package com.zhiguw.file.enums;

/**
 * @ClassName: FileType.java
 * @Author: liukailong
 * @Description: 文件 类型枚举常量
 * @Date: 2015年4月7日
 * 
 */
public enum FileType {
	
	File("file"), Image("image");
	
	private String type;
	
	private FileType(String type){
		
		this.type=type;
		
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
