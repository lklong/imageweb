/**
 * 
 */
package com.zhiguw.file.entity;

import java.util.Arrays;
import java.util.List;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Value;

/**
 * @ClassName: ImageProperties.java
 * @Author: liukailong
 * @Description:图片属性
 * 
 */
@Named
public class ImageProperties {

	/** 图片最大值 */
	@Value("${image.max.size}")
	private String maxSize;

	/** 图片存放路径 */
	@Value("${image.path.real}")
	private String descPath;

	/** 图片后缀 */
	@Value("${image.suffix}")
	private List<String> imageSuffix;

	/** 商品图片规格 */
	@Value("${image.goods.specs}")
	private List<String> goodsImageSpecs;

	/** 店铺图片规格 */
	@Value("${image.store.specs}")
	private List<String> storeImageSpecs;

	/** 头像图片规格 */
	@Value("${image.head.specs}")
	private List<String> headImageSpecs;
	
	/** 图片服务器地址 */
	@Value("${image.path.host}")
	private String host;
	
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(String maxSize) {
		this.maxSize = maxSize;
	}

	public String getDescPath() {
		return descPath;
	}

	public void setDescPath(String descPath) {
		this.descPath = descPath;
	}

	public List<String> getImageSuffix() {
		return Arrays.asList(imageSuffix.get(0).split(","));
	}

	public void setImageSuffix(List<String> imageSuffix) {
		this.imageSuffix = imageSuffix;
	}

	public List<String> getGoodsImageSpecs() {
		return Arrays.asList(goodsImageSpecs.get(0).split(","));
	}

	public void setGoodsImageSpecs(List<String> goodsImageSpecs) {
		this.goodsImageSpecs = goodsImageSpecs;
	}

	public List<String> getStoreImageSpecs() {
		return Arrays.asList(storeImageSpecs.get(0).split(","));
	}

	public void setStoreImageSpecs(List<String> storeImageSpecs) {
		this.storeImageSpecs = storeImageSpecs;
	}

	public List<String> getHeadImageSpecs() {
		return Arrays.asList(headImageSpecs.get(0).split(","));
	}

	public void setHeadImageSpecs(List<String> headImageSpecs) {
		this.headImageSpecs = headImageSpecs;
	}

}
