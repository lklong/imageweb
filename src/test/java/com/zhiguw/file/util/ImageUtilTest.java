package com.zhiguw.file.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import org.apache.commons.io.FilenameUtils;
import org.im4java.core.CompositeCmd;
import org.im4java.core.ConvertCmd;
import org.im4java.core.IM4JavaException;
import org.im4java.core.IMOperation;
import org.im4java.core.IdentifyCmd;
import org.im4java.core.Info;
import org.im4java.core.InfoException;
import org.im4java.core.Operation;
import org.im4java.process.ArrayListOutputConsumer;
import org.im4java.process.Pipe;
import org.im4java.process.ProcessStarter;
import org.im4java.process.StandardStream;
import org.junit.Before;
import org.junit.Test;

public class ImageUtilTest {
	

	private static final String GLOBLE_PATH = "C:\\Program Files\\GraphicsMagick-1.3.19-Q8";

	private static final String INDIVIDUAL_PATH = "C:\\Program Files\\GraphicsMagick-1.3.19-Q8";

	private static final String WATERMARK = "www.zhiguw.com";

	private static final String MARK_PARAM = "text 0,0 ";

	private static final Boolean GM = true;

	private static final String FONT = "Arial";

	private static final String COLOR = "yellow";

	private static String PIC_PATH = "F:/static/8d.jpg";

	private static String NEW_PIC_PATH = "F:/static/8d_19_11_12344.jpg";

	@Before
	public void setGloblePath() {

		String osName = System.getProperty("os.name").toLowerCase();

		if (osName.indexOf("win") >= 0) { // linux下不要设置此值，不然会报错

			ProcessStarter.setGlobalSearchPath(GLOBLE_PATH);

		}

	}

	// @Before
	public void setIndividuallPath() {

		ConvertCmd cmd = new ConvertCmd();

		String osName = System.getProperty("os.name").toLowerCase();

		if (osName.indexOf("win") >= 0) {
			// linux下不要设置此值，不然会报错
			cmd.setSearchPath(INDIVIDUAL_PATH);
		}

		cmd.setErrorConsumer(StandardStream.STDERR);

	}

	/**
	 * 直接裁切图片
	 * 
	 * 上午11:59:14 Administrator void
	 *
	 */
//	@Test
	public static void resize() {

		// create command
		ConvertCmd cmd = new ConvertCmd(GM);

		// create the operation, add images and operators/options
		IMOperation op = new IMOperation();
		op.addImage(PIC_PATH);
		op.resize(800, 200, "^");
		op.quality(10.0);
		op.addImage(NEW_PIC_PATH);

		try {
			// execute the operation
			cmd.run(op);
			op.closeOperation();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IM4JavaException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 流裁切图片
	 * 
	 * @throws FileNotFoundException
	 *             上午11:59:32 Administrator void
	 *
	 */
	@Test
	public void resize_1() throws FileNotFoundException {

		InputStream is = new FileInputStream(new File(PIC_PATH));

		OutputStream os = new FileOutputStream(new File(NEW_PIC_PATH));

		IMOperation op = new IMOperation();
		op.addImage("-");
		op.resize(200, 200);
		op.addImage("-");
		Pipe pipe = new Pipe(is, os);

		// set up command
		ConvertCmd convert = new ConvertCmd(GM);
		convert.setInputProvider(pipe);
		convert.setOutputConsumer(pipe);

		try {
			convert.run(op);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IM4JavaException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

	/**
	 * 头像处理
	 * 
	 * 先缩放，后居中切割图片
	 * 
	 * @param srcPath
	 *            源图路径
	 * @param desPath
	 *            目标图保存路径
	 * @param rectw
	 *            待切割在宽度
	 * @param recth
	 *            待切割在高度
	 * @throws IM4JavaException
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public static void cropImageCenter(String srcPath, String desPath, int rectw, int recth) throws IOException, InterruptedException, IM4JavaException {
		IMOperation op = new IMOperation();

		op.addImage(srcPath);
		op.resize(rectw, recth, '^').gravity("center").extent(rectw, recth);
		op.addImage(desPath);

		ConvertCmd convert = new ConvertCmd(GM);
		convert.run(op);

		// 生成命令脚本文件
		// convert.createScript("E:\\myscript.sh", op);

	}

	/**
	 * 
	 * 根据坐标裁剪图片
	 * 
	 * @param srcPath
	 *            要裁剪图片的路径
	 * @param newPath
	 *            裁剪图片后的路径
	 * @param x
	 *            起始横坐标
	 * @param y
	 *            起始纵坐标
	 * @param x1
	 *            结束横坐标
	 * @param y1
	 *            结束纵坐标
	 */

	public static void cutImage(String srcPath, String newPath, int x, int y, int x1, int y1) throws Exception {
		int width = x1 - x;
		int height = y1 - y;
		IMOperation op = new IMOperation();
		op.addImage(srcPath);
		op.crop(width, height, x, y);
		op.addImage(newPath);

		ConvertCmd convert = new ConvertCmd(GM);
		convert.run(op);
	}

	/**
	 * 加文字水印
	 * 
	 * @param srcPath
	 * @param newPath
	 * @param watermark
	 * @param markparam
	 * @param font
	 * @param color
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws IM4JavaException
	 *
	 */
	public static void addWordWaterMark(String srcPath, String newPath, String watermark, String markparam, String font, String color) throws IOException, InterruptedException, IM4JavaException {
		IMOperation op = new IMOperation();

		op.addImage(srcPath);
		op.gravity("southeast");
		op.font(font).fill(color).draw(markparam + watermark);
		op.quality(100.00);
		op.addImage(newPath);

		ConvertCmd convert = new ConvertCmd(GM);
		convert.run(op);

	}

	/**
	 * 图片水印
	 * 
	 * @param srcPath
	 * @param imageMark
	 * @param newPath
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws IM4JavaException
	 *             下午2:02:51 Administrator void
	 *
	 */
	public static void addImageWaterMark(String srcPath, String imageMark, String newPath) throws IOException, InterruptedException, IM4JavaException {
		IMOperation op = new IMOperation();

		op.addImage(srcPath);
		op.addImage(imageMark);
		op.gravity("southeast").dissolve(100);
		op.addImage(newPath);

		CompositeCmd cmd = new CompositeCmd(GM);

		cmd.run(op);
	}

	public static String showImageInfo(String imagePath) {
		String line = null;
		try {
			IMOperation op = new IMOperation();
			op.format("width:%w,height:%h,path:%d%f,size:%b%[EXIF:DateTimeOriginal]");
			op.addImage(1);
			IdentifyCmd identifyCmd = new IdentifyCmd(true);
			ArrayListOutputConsumer output = new ArrayListOutputConsumer();
			identifyCmd.setOutputConsumer(output);
			identifyCmd.run(op, imagePath);
			ArrayList<String> cmdOutput = output.getOutput();
			assert cmdOutput.size() == 1;
			line = cmdOutput.get(0);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return line;
	}

	@Test
	public void test_2() throws IOException, InterruptedException, IM4JavaException {

		ConvertCmd cvcmd = new ConvertCmd(GM);

		Operation op = new Operation();
		op.addImage(PIC_PATH);
		op.addRawArgs("-sample", "600x600^");
		op.addRawArgs("-gravity", "center");
		op.addRawArgs("-extent", "600x600");
		op.addRawArgs("-quality", "100");
		op.addImage(NEW_PIC_PATH);

		cvcmd.run(op);

	}

	@Test
	public void test_3() throws InfoException {

		Info imageInfo = new Info(PIC_PATH, true);
		System.out.println("Format: " + imageInfo.getImageFormat());
		System.out.println("Width: " + imageInfo.getImageWidth());
		System.out.println("Height: " + imageInfo.getImageHeight());
		System.out.println("Geometry: " + imageInfo.getImageGeometry());
		System.out.println("Depth: " + imageInfo.getImageDepth());
		System.out.println("Class: " + imageInfo.getImageClass());

	}

	@Test
	public void testAddImage() throws IOException, InterruptedException, IM4JavaException {

		addImageWaterMark("F:/static/8d_4.jpg", PIC_PATH, NEW_PIC_PATH);

	}

	@Test
	public void testAdd() throws IOException, InterruptedException, IM4JavaException {
		addWordWaterMark(PIC_PATH, NEW_PIC_PATH, WATERMARK, MARK_PARAM, FONT, COLOR);
	}

	@Test
	public void cropImageCenter() throws IOException, InterruptedException, IM4JavaException {
		cropImageCenter(PIC_PATH, NEW_PIC_PATH, 100, 100);
	}

	@Test
	public void test() throws Exception {
		cutImage(PIC_PATH, NEW_PIC_PATH, 0, 0, 100, 100);
	}

	@Test
	public void test_4() {

		System.out.println(FilenameUtils.getExtension(PIC_PATH));

	}

}
