package com.zhiguw.file.entity;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * 
 * @ClassName: ZhiguFile.java
 * @Author: liukailong
 * @Description:文件,图片实体
 * @Date: 2015年4月3日
 *
 */
public class ZhiguFile implements Serializable {

	/** long */
	private static final long serialVersionUID = 4101339373394831065L;

	/** id */
	private Long id;

	/** 用户id */
	private Integer userID;

	/** 删除时间 */
	private Date createTime = new Date();

	/** 文件类型（1.文件，2.图片） */
	private Boolean fileType = true;

	/** 相对路径 */
	private String uri;

	/** 硬盘位置 */
	private String realPath;

	/** 图片宽 */
	private Integer imageWidth;

	/** 图片高 */
	private Integer imageHeight;

	/** 文件大小 */
	private Long size;

	/** 规格数组 */
	private String specs;

	/** 删除时间 */
	private Date deleteFime = new Date();

	/** 删除标示 */
	private Boolean deleteFlag = false;
	
	/** 服务地址 */
	private String host;
	
	/** 完整路径 */
	private String wholePath;
	
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getWholePath() {
		return wholePath;
	}

	public void setWholePath(String wholePath) {
		this.wholePath = wholePath;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Boolean getFileType() {
		return fileType;
	}

	public void setFileType(Boolean fileType) {
		this.fileType = fileType;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri == null ? null : uri.trim();
	}

	public String getRealPath() {
		return realPath;
	}

	public void setRealPath(String realPath) {
		this.realPath = realPath == null ? null : realPath.trim();
	}

	public Integer getImageWidth() {
		return imageWidth;
	}

	public void setImageWidth(Integer imageWidth) {
		this.imageWidth = imageWidth;
	}

	public Integer getImageHeight() {
		return imageHeight;
	}

	public void setImageHeight(Integer imageHeight) {
		this.imageHeight = imageHeight;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public String getSpecs() {
		return specs;
	}

	public void setSpecs(String specs) {
		this.specs = specs == null ? null : specs.trim();
	}

	public Date getDeleteFime() {
		return deleteFime;
	}

	public void setDeleteFime(Date deleteFime) {
		this.deleteFime = deleteFime;
	}

	public Boolean getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		return ReflectionToStringBuilder.reflectionToString(this);
	}
}