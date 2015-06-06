/**
 * 
 */
package com.zhiguw.file.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zhiguw.file.entity.Result;
import com.zhiguw.file.entity.ZhiguFile;
import com.zhiguw.file.exception.FileUploadException;
import com.zhiguw.file.service.FileUploadService;

/**
 * @ClassName: FileController.java
 * @Author: liukailong
 * @Description:文件,图片管理控制器
 * 
 */
@Controller
public class FileController {
	
	/** 日志记录器 */
	private static final Logger LOGGER = LoggerFactory.getLogger(FileController.class);
	
	/** 文件,图片上传业务层 */
	@Inject
	private FileUploadService fileUploadService;

	/**
	 * 上传初始化
	 * @return
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public String provideUploadInfo() {

		return "index";

	}

	/**
	 * 
	 * @param type
	 *            文件类型(数据包：file,图片:image(默认值))
	 * @param file
	 *            文件
	 * @param imageType
	 *            图片来源(商品：goods(默认值),头像：head,店铺：store)
	 * @param specs
	 *            裁切规格
	 * @param userId
	 * 			  用户id(默认123456)
	 * @return 
	 *
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public Result upload(
			@RequestParam(value = "type", defaultValue = "Image") String type, 
			@RequestParam("file") MultipartFile file,
			@RequestParam(value = "imageType", defaultValue = "goods") String imageType, 
			@RequestParam(value="useId",defaultValue="123456")Integer useId,
			String specs ) {

		Result jsonResult = new Result();

		String result = "success";

		String msg = "";
		
		int errorCode = 0;

		ZhiguFile zhiguFile = null;

		// 基本信息验证
		jsonResult = fileUploadService.validateFile(file, type);

		if (jsonResult.getErrorCode() == 1) {

			return jsonResult;

		}

		try {

			// 业务处理
			zhiguFile = fileUploadService.uploadZhiguFile(file, type, imageType, useId, specs);

		} catch (FileUploadException e) {

			msg = "upload failed";
			
			result= "error";

			errorCode = 1;
			
		} 

		jsonResult.setMsg(msg);

		jsonResult.setErrorCode(errorCode);
		
		jsonResult.setResult(result);

		jsonResult.setData(zhiguFile);

		return jsonResult;

	}
}
