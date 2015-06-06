/**
 * 
 */
package com.zhiguw.file.exception;

/**
 * @ClassName: FileUploadException.java
 * @Author: liukailong
 * @Description: 文件上传业务异常
 * @Date: 2015年4月3日
 * 
 */
public class FileUploadException extends RuntimeException {

	/** long */
	private static final long serialVersionUID = -5973973427077796921L;

	public FileUploadException() {
		super();
	}

	public FileUploadException(Exception e) {
		super(e);
	}

	public FileUploadException(String message) {
		super(message);
	}

	public FileUploadException(String message, Exception e) {
		super(message, e);
	}

}
