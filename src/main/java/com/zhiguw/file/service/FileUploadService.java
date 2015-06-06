/**
 * 
 */
package com.zhiguw.file.service;

import org.springframework.web.multipart.MultipartFile;

import com.zhiguw.file.entity.Result;
import com.zhiguw.file.entity.ZhiguFile;
import com.zhiguw.file.exception.FileUploadException;

/**
 * @ClassName: FileUploadService.java
 * @Author: liukailong
 * @Description: 文件上传业务层
 * @Date: 2015年4月3日
 * 
 */
public interface FileUploadService {

	/**
	 * 数据上传业务处理
	 * @param file 上传的数据
	 * @param type 数据类型（文件：file,图片：image）
	 * @param imageType 图片类型（商品：goods,店铺：store,头像:head）
	 * @param useId 用户id
	 * @param specs 图片规格
	 * @return
	 * @throws FileUploadException
	 */
	ZhiguFile uploadZhiguFile(MultipartFile file, String type, String imageType, Integer useId, String specs) throws FileUploadException;
	
	/**
	 * 数据验证
	 * @param file 上传的数据
	 * @param type 数据类型（文件：file,图片：image）
	 * @return
	 */
	public Result validateFile(MultipartFile file, String type);

}
