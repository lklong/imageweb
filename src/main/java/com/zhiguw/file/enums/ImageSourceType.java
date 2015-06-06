/**
 * 
 */
package com.zhiguw.file.enums;

/**
 * @ClassName: ImageSourceType.java
 * @Author: liukailong
 * @Description: 图片来源枚举常量
 * @Date: 2015年4月7日
 * 
 */
public enum ImageSourceType {

	Goods("goods"), Store("store"), Head("head");
	
	private String type;
	
	private ImageSourceType(String type){
		
		this.type = type;
		
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	

}
