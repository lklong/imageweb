/**
 * 
 */
package com.zhiguw.file.util;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.im4java.core.CompositeCmd;
import org.im4java.core.ConvertCmd;
import org.im4java.core.GMOperation;
import org.im4java.core.IM4JavaException;
import org.im4java.core.IMOperation;
import org.im4java.core.IdentifyCmd;
import org.im4java.process.ArrayListOutputConsumer;
import org.im4java.process.ProcessStarter;

import com.zhiguw.file.constants.GraphicImageConstants;

/**
 * @ClassName: ImageUtil2.java
 * @Author: liukailong
 * @Description:图片裁切工具类
 * @Date: 2015年4月7日
 * 
 */
public class ImageUtil {

	/**
	 * 图片压缩 照宽高比例不变
	 * 
	 * @param width
	 *            宽度
	 * @param height
	 *            高度
	 * @param is
	 * @param os
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws IM4JavaException
	 *
	 */
	public static void resizeImageKeepScale(Integer width, Integer height, Integer nWidth, Integer nHeight,BufferedImage img, String descPath) throws IOException, InterruptedException, IM4JavaException {

		if (nHeight >= height) {

			nHeight = height;

		}

		if (nWidth >= width) {

			nWidth = width;

		}
		
		GMOperation op = new GMOperation();
		
		op.addImage();
		
		op.addRawArgs("-quality","100.0");
		
		op.resize(nWidth, nHeight);
		
		//去掉图片所有内置信息
		op.addRawArgs("+profile","*");
		
		op.addImage();
		
		ConvertCmd cmd = new ConvertCmd(GraphicImageConstants.GM);

		cmd.run(op,img,descPath);
		
	}
	
	/**
	 * 图片压缩 严格按照指定规格压缩图片
	 * 
	 * @param width
	 *            宽度
	 * @param height
	 *            高度
	 * @param is
	 * @param os
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws IM4JavaException
	 *
	 */
	public static void resizeImage(Integer width, Integer height, Integer nWidth, Integer nHeight, BufferedImage img, String descPath) throws IOException, InterruptedException, IM4JavaException {

		if (nHeight >= height) {

			nHeight = height;

		}

		if (nWidth >= width) {

			nWidth = width;

		}

		IMOperation op = new IMOperation();
		
		op.addImage();

		op.resize(nWidth, nHeight, "!");
		
		//去掉图片所有内置信息
		op.addRawArgs("+profile","*");
		
		op.addRawArgs("-quality","90.0");

		op.addImage();
		
		ConvertCmd cmd = new ConvertCmd(GraphicImageConstants.GM);

		cmd.run(op,img,descPath);

	}

	/**
	 * 图片先压缩后裁切
	 * 
	 * @param width
	 *            压缩宽度
	 * @param height
	 *            压缩高度
	 * 
	 * @param eWidth
	 *            裁切宽度
	 * @param eHeight
	 *            裁切高度
	 * 
	 * @param is
	 * @param os
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws IM4JavaException
	 *
	 */
	public static void pressAndCutImage(Integer width, Integer height, Integer nWidth, Integer nHeight, Integer eWidth, Integer eHeight, BufferedImage img, String descPath) throws IOException,
			InterruptedException, IM4JavaException {

		if (nHeight >= height) {

			nHeight = height;

		}

		if (nWidth >= width) {

			nWidth = width;

		}

		IMOperation op = new IMOperation();
		
		op.addImage();

		op.resize(nWidth, nHeight, "^").extent(eWidth, eHeight);
		
		//去掉图片所有内置信息
		op.addRawArgs("+profile","*");
		
		op.addRawArgs("-quality","90.0");

		op.addImage();

		ConvertCmd cmd = new ConvertCmd(GraphicImageConstants.GM);

		cmd.run(op,img,descPath);

	}

	/**
	 * 图片先裁切后压缩
	 * 
	 * @param width
	 *            压缩宽度
	 * @param height
	 *            压缩高度
	 * 
	 * @param eWidth
	 *            裁切宽度
	 * @param eHeight
	 *            裁切高度
	 * 
	 * @param is
	 * @param os
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws IM4JavaException
	 *
	 */
	public static void cutAndPressImage(Integer width, Integer height, Integer eWidth, Integer eHeight, Integer nWidth, Integer nHeight, BufferedImage img, String descPath) throws IOException,
			InterruptedException, IM4JavaException {

		if (eHeight >= height) {

			eHeight = height;

		}

		if (eWidth >= width) {

			eWidth = width;

		}

		IMOperation op = new IMOperation();
		
		op.addImage();

		op.extent(eWidth, eHeight).resize(nWidth, nHeight, "^");
		
		//去掉图片所有内置信息
		op.addRawArgs("+profile","*");
		
		op.addRawArgs("-quality","90.0");

		op.addImage();

		ConvertCmd cmd = new ConvertCmd(GraphicImageConstants.GM);

		cmd.run(op,img,descPath);
		
	}

	/**
	 * 头像处理 先压缩再裁剪
	 * 
	 * @param is
	 * @param os
	 * @param rectw
	 * @param recth
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws IM4JavaException
	 *
	 */
	public static void cropImageCenter(BufferedImage img, String descPath, int rectw, int recth) throws IOException, InterruptedException, IM4JavaException {

		IMOperation op = new IMOperation();
		
		op.addImage();

		op.resize(rectw, recth, '^').gravity("center").extent(rectw, recth).quality(100d);
		
		//去掉图片所有内置信息
		op.addRawArgs("+profile","*");
		
		op.addRawArgs("-quality","90.0");

		op.addImage();
		
		ConvertCmd cmd = new ConvertCmd(GraphicImageConstants.GM);

		cmd.run(op,img,descPath);

	}

	/**
	 * 图片裁切 根据坐标处理图片
	 * 
	 * @param is
	 * @param os
	 * @param height
	 *            原始高度
	 * @param width
	 *            原始宽度
	 * @param x
	 *            起点横坐标
	 * @param y
	 *            起点纵坐标
	 * @param x1
	 *            终点横坐标
	 * @param y1
	 *            终点纵坐标
	 * @throws Exception
	 *
	 */
	public static void cutImageByPoint(BufferedImage img, String descPath, Integer height, Integer width, Integer x, Integer y, Integer x1, Integer y1) throws Exception {

		int nWidth = x1 - x;

		int nHeight = y1 - y;

		cutImageByPointAndWH(img, descPath, height, width, nWidth, nHeight, x, y);

	}

	/**
	 * 图片裁切 根据坐标,指定宽高处理图片
	 * 
	 * @param is
	 * @param os
	 * @param height
	 *            原始高度
	 * @param width
	 *            原始宽度
	 * @param x
	 *            起点横坐标
	 * @param y
	 *            起点纵坐标
	 * @throws Exception
	 *
	 */
	public static void cutImageByPointAndWH(BufferedImage img, String descPath, Integer height, Integer width, Integer nWidth, Integer nHeight, Integer x, Integer y) throws Exception {

		IMOperation op = new IMOperation();

		if (nHeight >= height) {

			nHeight = height;

		}

		if (nWidth >= width) {

			nWidth = width;

		}
		
		op.addImage();

		op.crop(nWidth, nHeight, x, y);

		//去掉图片所有内置信息
		op.addRawArgs("+profile","*");
		
		op.addRawArgs("-quality","90.0");
		
		op.addImage();

		ConvertCmd cmd = new ConvertCmd(GraphicImageConstants.GM);

		cmd.run(op,img,descPath);
	}

	/**
	 * 
	 * 先根据坐标裁切,再进行压缩
	 * 
	 * @param is
	 * @param os
	 * @param height
	 * @param width
	 * @param x
	 * @param y
	 * @param x1
	 * @param y1
	 * @param eWidth
	 * @param eHeight
	 * @throws Exception
	 *
	 */
	public static void cutImageByPointAndPress(BufferedImage img, String descPath, Integer height, Integer width, Integer x, Integer y, Integer x1, Integer y1, Integer eWidth, Integer eHeight)
			throws Exception {

		int nWidth = x1 - x;

		int nHeight = y1 - y;

		cutImageByPointWHAndPress(img, descPath, height, width, x, y, nWidth, nHeight, eWidth, eHeight);

	}

	/**
	 * 先根据坐标和指定宽高裁切,再进行压缩
	 * 
	 * @param is
	 * @param os
	 * @param height
	 * @param width
	 * @param x
	 * @param y
	 * @param nWidth
	 * @param nHeight
	 * @param eWidth
	 * @param eHeight
	 * @throws Exception
	 *
	 */
	public static void cutImageByPointWHAndPress(BufferedImage img, String descPath, Integer height, Integer width, Integer x, Integer y, Integer nWidth, Integer nHeight, Integer eWidth, Integer eHeight)
			throws Exception {

		if (nHeight >= height) {

			nHeight = height;

		}

		if (nWidth >= width) {

			nWidth = width;

		}

		IMOperation op = new IMOperation();
		
		op.addImage();

		op.crop(nWidth, nHeight, x, y).resize(eWidth, eHeight);

		//去掉图片所有内置信息
		op.addRawArgs("+profile","*");
		
		op.addRawArgs("-quality","90.0");
		
		op.addImage();

		ConvertCmd cmd = new ConvertCmd(GraphicImageConstants.GM);

		cmd.run(op,img,descPath);

	}

	/**
	 * 给图片添加文字水印
	 * 
	 * @param is
	 * @param os
	 * @param watermark
	 *            水印内容
	 * @param markparam
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws IM4JavaException
	 *
	 */
	public static void addWordWaterMark(BufferedImage img, String descPath, String markparam) throws IOException, InterruptedException, IM4JavaException {

		IMOperation op = new IMOperation();
		
		op.addImage();

		op.font(GraphicImageConstants.FONT);

		op.fill(GraphicImageConstants.COLOR);

		op.pointsize(GraphicImageConstants.POINTSIZE);

		op.gravity(GraphicImageConstants.GRAVITY);

		op.dissolve(GraphicImageConstants.DISSOLVE);

		op.draw(markparam + GraphicImageConstants.WATERMARK);

		//去掉图片所有内置信息
		op.addRawArgs("+profile","*");
		
		op.addRawArgs("-quality","90.0");
		
		op.addImage();

		ConvertCmd cmd = new ConvertCmd(GraphicImageConstants.GM);

		cmd.run(op,img,descPath);

	}

	/**
	 * 给图片添加图片水印
	 * 
	 * @param waterPic
	 *            水印图片
	 * @param is
	 * @param os
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws IM4JavaException
	 *
	 */
	public static void addImageWaterMark(String waterPic, BufferedImage img, String descPath) throws IOException, InterruptedException, IM4JavaException {

		IMOperation op = new IMOperation();

		op.addImage(waterPic);

		op.gravity(GraphicImageConstants.GRAVITY);

		op.dissolve(GraphicImageConstants.DISSOLVE);

		//去掉图片所有内置信息
		op.addRawArgs("+profile","*");
		
		op.addRawArgs("-quality","90.0");
		
		op.addImage();

		CompositeCmd cmd = new CompositeCmd(GraphicImageConstants.GM);
		
		cmd.run(op, img,descPath);

	}

	/**
	 * 获取图片基本信息
	 * 
	 * @param is
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws IM4JavaException
	 *
	 */
	public static List<Integer> getImageInfo(InputStream is) throws IOException, InterruptedException, IM4JavaException {
		
		List<Integer> imagewh = new ArrayList<Integer>();

		IMOperation op = new IMOperation();

		op.format("%w,%h");// ,path:%d%f,size:%b%[EXIF:DateTimeOriginal]

		op.addImage(1);

		IdentifyCmd cmd = new IdentifyCmd(GraphicImageConstants.GM);

		ArrayListOutputConsumer output = new ArrayListOutputConsumer();

		BufferedImage img = ImageIO.read(is);

		cmd.setOutputConsumer(output);

		cmd.run(op, img);

		ArrayList<String> cmdOutput = output.getOutput();

		String[] line = cmdOutput.get(0).split(",");

		Integer width = Integer.valueOf(line[0]);

		Integer height = Integer.valueOf(line[1]);

		imagewh.add(width);

		imagewh.add(height);

		return imagewh;

	}
	
	static {

		String osName = System.getProperty("os.name").toLowerCase();

		if (osName.indexOf("win") >= 0) { // linux下不要设置此值，不然会报错

			ProcessStarter.setGlobalSearchPath(GraphicImageConstants.GLOBLE_PATH);

		}

	}


}
