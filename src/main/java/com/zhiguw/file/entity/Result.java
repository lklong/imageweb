package com.zhiguw.file.entity;


	import java.io.Serializable;

	/**
	 * 返回结果类
	 * 
	 * @ClassName: Result
	 * @Description: 定义业务处理返回内容
	 * @author hesimin
	 * @date 2015年3月30日 上午10:45:37
	 *
	 */
	public class Result implements Serializable {

		private static final long serialVersionUID = 7364797910861994754L;
		/**
		 * 错误代码（0：操作成功）
		 */
		private int errorCode;
		/**
		 * 执行结果（'success'，'error'，'warning'）
		 */
		private String result;
		/**
		 * 消息信息
		 */
		private String msg;
		/**
		 * 返回数据
		 */
		private Object data;
		/**
		 * 构造方法
		 * 
		 * @param result
		 *            处理结果
		 */
		public Result(String result) {
			this.result = result;
		}

		/**
		 * 构造方法
		 * 
		 * @param result
		 *            处理结果
		 * @param msgs
		 *            消息内容
		 */
		public Result(String result, int errorCode, String msg) {
			this.errorCode = errorCode;
			this.msg = msg;
			this.result = result;
		}
		
		/**
		 * 构造方法
		 */
		public Result() {
		}

		public int getErrorCode() {
			return errorCode;
		}

		public void setErrorCode(int errorCode) {
			this.errorCode = errorCode;
		}

		public String getResult() {
			return result;
		}

		public void setResult(String result) {
			this.result = result;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}

		public Object getData() {
			return data;
		}

		public void setData(Object data) {
			this.data = data;
		}



}
