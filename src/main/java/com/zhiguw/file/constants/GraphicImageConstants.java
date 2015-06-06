/**
 * 
 */
package com.zhiguw.file.constants;


/**
 * @ClassName: GraphicImageConstants.java
 * @Author: liukailong
 * @Description: 图片裁切常量
 * @Date: 2015年4月7日
 * 
 */
public interface GraphicImageConstants {

	/** 全局工具路径 linux环境下不需要此路径*/
	String GLOBLE_PATH = "D:\\GraphicsMagick-1.3.21-Q8";

	/** 个人工具配置路径 linux环境下不需要此路径*/
	String INDIVIDUAL_PATH = "D:\\GraphicsMagick-1.3.21-Q8";

	/** 水印内容 */
	String WATERMARK = "www.zhiguw.com";

	/** 水印格式，位置起点 */
	String MARK_PARAM = "text 0,0 ";

	/** 文件,图片后缀名 */
	String FILE_NAME_SUFFIX = "zhiguw";

	/** 是否使用gm处理图片，false/null:使用im处理图片 */
	Boolean GM = true;

	/** 水印字体 */
	String FONT = "Arial";

	/** 水印颜色 */
	String COLOR = "yellow";

	/** 透明度 */
	Integer DISSOLVE = 50;

	/** 水印重心 */
	String GRAVITY = "southeast";

	/** 字体粗度 */
	Integer POINTSIZE = 50;
	
}
