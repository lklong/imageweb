/**
 * 
 */
package com.zhiguw.file.service.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.zhiguw.file.entity.FileProperties;
import com.zhiguw.file.entity.ImageProperties;
import com.zhiguw.file.entity.Result;
import com.zhiguw.file.entity.ZhiguFile;
import com.zhiguw.file.enums.FileType;
import com.zhiguw.file.enums.ImageSourceType;
import com.zhiguw.file.exception.FileUploadException;
import com.zhiguw.file.mapper.ZhiguFileMapper;
import com.zhiguw.file.service.FileUploadService;
import com.zhiguw.file.util.CmykImageUtil;
import com.zhiguw.file.util.FileNameUtil;
import com.zhiguw.file.util.FileUtil;
import com.zhiguw.file.util.ImageUtil;

/**
 * @ClassName: FileUploadServiceImpl.java
 * @Author: liukailong
 * @Description: 文件上传业务层实现
 * @Date: 2015年4月3日
 * 
 */
@Service
public class FileUploadServiceImpl implements FileUploadService {
	
	/** 日子记录器  */
	private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadServiceImpl.class);
	
	/** 注入文件上传数据层接口  */
	@Inject
	private ZhiguFileMapper zhiguFileMapper;

	/** 注入文件属性 */
	@Inject
	private FileProperties fileProperties;

	/** 注入图片属性 */
	@Inject
	private ImageProperties imageProperties;
	
	@Override
	public Result validateFile(MultipartFile file, String type) {

		String result = "success";

		int errorCode = 0;
		
		String msg = "pass validate";

		/*
		 *  1.检查文件是否为空
		 *  2.检查文件大小 
		 *	3.检查文件类型
		 */
		if (file == null) {

			msg = "file is empty";

			errorCode = 1;
			
			result = "error";

			return new Result(result,errorCode,msg);

		}

		Long size = file.getSize();

		String name = file.getOriginalFilename();

		String suffix = FilenameUtils.getExtension(name);

		// 数据包
		if (type.equals(FileType.File.getType())) {

			// 文件大小的验证
			if (size > Long.valueOf(fileProperties.getMaxSize())) {

				msg = "file size is too big";

				errorCode = 1;
				
				result = "error";

				return new Result(result,errorCode,msg);

			}

			// 文件类型的验证
			if (!fileProperties.getFileSuffix().contains(suffix)) {

				msg = "file type is not correct";

				errorCode = 1;
				
				result = "error";

				return new Result(result,errorCode,msg);

			}

		} else {

			// 图片大小的验证
			if (size > Long.valueOf(imageProperties.getMaxSize())) {

				msg = "image size is too big";

				errorCode = 1;
				
				result = "error";

				return new Result(result,errorCode,msg);

			}

			// 图片类型的验证
			if (!imageProperties.getImageSuffix().contains(suffix.toLowerCase())) {

				msg = "image type is not correct";

				errorCode = 1;
				
				result = "error";

				return new Result(result,errorCode,msg);

			}

		}

		return new Result(result,errorCode,msg);

	}

	@Override
	public ZhiguFile uploadZhiguFile(MultipartFile file, String type, String imageType, Integer useId, String specs) throws FileUploadException {

		String descPath = null;
		
		String host = null;
		
		String wholePath = null;

		ZhiguFile zhiguFile = new ZhiguFile();

		String riginalName = file.getOriginalFilename();

		String uri = FileNameUtil.generateFileName("", riginalName);
		
		try {
			
			InputStream is = file.getInputStream();
	
			// 转存文件
			if (type.equals(FileType.File.getType())) {
	
				descPath = fileProperties.getDescPath() + uri;
				
				host = fileProperties.getHost();
				
				wholePath = host+uri;
	
				FileUtils.copyInputStreamToFile(is, new File(descPath));
				
				// 转存图片
			} else {
	
				descPath = imageProperties.getDescPath() + uri;
				
				host = imageProperties.getHost();
				
				wholePath = host+uri;
				
				File newFile = new File(descPath);
	
				FileUtils.copyInputStreamToFile(is, newFile);
				
				BufferedImage img = CmykImageUtil.readImage(newFile);
	
				Integer width = img.getWidth();
	
				Integer height = img.getHeight();
	
				zhiguFile.setImageWidth(width);
	
				zhiguFile.setImageHeight(height);
				
				List<String> imageSpecs = getSpecs(specs, imageType);
	
				proccessImage(imageSpecs, width, height, img,descPath);
					
				// 关闭流
				is.close();
				
				zhiguFile.setSpecs(imageSpecs.toString());
			}
			
		} catch (Exception e) {
			
			LOGGER.error("图片裁切错误："+e.getMessage());
			
		}
		
		zhiguFile.setSize(file.getSize());

		zhiguFile.setUri(uri);

		zhiguFile.setRealPath(descPath);

		zhiguFile.setFileType(type.equals(FileType.File.toString()));

		zhiguFile.setUserID(useId);

		// 存入数据库
		zhiguFileMapper.add(zhiguFile);
		
		zhiguFile.setHost(host);
		
		zhiguFile.setWholePath(wholePath);
		
		return zhiguFile;
	}
	
	/**
	 * 获取图片规格
	 * @param specs
	 * @param imageType
	 * @return
	 */
	private List<String> getSpecs(String specs, String imageType) {

		List<String> imagespecs = new ArrayList<String>();

		if (StringUtils.isBlank(specs)) {

			if (imageType.equals(ImageSourceType.Goods.getType())) {

				imagespecs = imageProperties.getGoodsImageSpecs();

			}

			if (imageType.equals(ImageSourceType.Store.getType())) {

				imagespecs = imageProperties.getStoreImageSpecs();

			}

			if (imageType.equals(ImageSourceType.Head.getType())) {

				imagespecs = imageProperties.getHeadImageSpecs();
			}

		} else {

			imagespecs = Arrays.asList(specs.split(","));

		}

		return imagespecs;

	}

	/**
	 * 
	 * @param specs
	 * @param imageType
	 * @param width
	 * @param height
	 * @param is
	 *            图片规格裁切说明： 
	 *            
	 *            1x320x320 --> 等比压缩图片，可能不符合规格，图片不变行  --压缩
	 *            2x320x320 --> 非等比压缩图片，完全符合规格，图片可能变形  --压缩
	 *            3x540x540x320x320 --> 先压缩（540x540）后裁剪 (320x320) 等比操作   --先压缩后裁切
	 *            4x540x540x320x320 --> 先裁切（540x540） 后压缩（320x320）等比操作 --先裁切后压缩
	 *            5x100x100x400x400 --> 根据图片坐标裁切图片 起点坐标：（100x100） 终点坐标 ：（400x400）--裁切
	 *            6x100x100x540x540 --> 根据图片坐标和指定宽高裁切图片  起点坐标：（100x100） 裁切宽高（540x540）--裁切
	 *            7x0x0x540x540x300x300 --> 先根据坐标裁切,再压缩,再裁切  起点坐标:（0x0）裁切宽高：（300x300）压缩宽高：（540x540）--先裁切后压缩
	 *            8x0x0x500x500x200x200 --> 先根据坐标裁切,再压缩 起点坐标：（100x100） 终点坐标 ：（500x500）压缩宽高（200x200） --先裁切后压缩
	 *            9x300x300 --> 头像简单处理
	 *            10x540x540x320x320 --> 先压缩（540x540）后裁剪 (320x320) 完全符合规格 --先压缩后裁切
	 *            11x540x540x320x320 --> 先裁切（540x540） 后压缩（320x320）完全符合规格 --先裁切后压缩
	 *            
	 */
	private void proccessImage(List<String> imageSpecs, Integer width, Integer height,BufferedImage img,  String srcPath) throws Exception {

		String descPath = null;

		for (int i = 0 ; i<imageSpecs.size();i++ ) {

			descPath = srcPath.replace("_0.", "_" +  (i+1)  + ".");

			String[] size = imageSpecs.get(i).split("x");

			Integer key = Integer.parseInt(size[0]);

			if (key == 1) {
				
					ImageUtil.resizeImageKeepScale(width, height, Integer.valueOf(size[1]), Integer.parseInt(size[2]), img, descPath);
					
				}

				else if(key == 2){
					
					ImageUtil.resizeImage(width, height, Integer.valueOf(size[1]), Integer.parseInt(size[2]), img, descPath);
				}

				else if(key == 3){
					
					ImageUtil.pressAndCutImage(width, height, Integer.valueOf(size[1]), Integer.parseInt(size[2]), Integer.valueOf(size[3]), Integer.parseInt(size[4]), img, descPath);
				}

				else if(key == 4){
					
					ImageUtil.cutAndPressImage(width, height, Integer.valueOf(size[1]), Integer.parseInt(size[2]), Integer.valueOf(size[3]), Integer.parseInt(size[4]), img, descPath);
				}

				else if(key == 5){
					
					ImageUtil.cutImageByPoint(img, descPath, height, width, Integer.parseInt(size[1]), Integer.parseInt(size[2]), Integer.parseInt(size[3]), Integer.parseInt(size[4]));
				}

				else if(key == 6){
					
					ImageUtil.cutImageByPointAndWH(img, descPath, height, width, Integer.parseInt(size[1]), Integer.parseInt(size[2]), Integer.parseInt(size[3]), Integer.parseInt(size[4]));
				}


				else if(key == 7){
					
					ImageUtil.cutImageByPointWHAndPress(img, descPath, height, width, Integer.parseInt(size[1]), Integer.parseInt(size[2]), Integer.parseInt(size[3]), Integer.parseInt(size[4]),
							Integer.parseInt(size[5]), Integer.parseInt(size[6]));
				}

				else if(key == 8){
					
					ImageUtil.cutImageByPointAndPress(img, descPath, height, width, Integer.parseInt(size[1]), Integer.parseInt(size[2]), Integer.parseInt(size[3]), Integer.parseInt(size[4]),
							Integer.parseInt(size[5]), Integer.parseInt(size[6]));
				
				}else if(key == 9){
					
					ImageUtil.cropImageCenter(img,descPath,Integer.parseInt(size[1]),Integer.parseInt(size[2]));
				}

			// 后续扩展
			//			TODO
			// case 9:
			//
			// break;
			// case 0:
			//
			// break;
			

		}
	}

}
