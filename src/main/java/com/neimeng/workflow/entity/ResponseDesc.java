package com.neimeng.workflow.entity;

/**
 * 业务类型描述集合
 *
 * 分为 :
 * 通用错误
 * 各自模块错误
 */
public class ResponseDesc {


	/**
	 * 系统基础类型
	 */
	public class System{
		/**
		 * failure
		 */
		public static final String FAILURE = "失败";

		/**
		 * success
		 */
		public static final String SUCCESS = "成功";

		/**
		 * no login
		 */
		public static final String NO_LOGIN = "please login first";

		/**
		 * no permission
		 */
		public static final String NO_PERMISSION = "no permission";

		/**
		 * no account
		 */
		public static final String NO_ACCOUNT = "账号不存在";

		/**
		 * account password error
		 */
		public static final String CERTIFICATE_ERROR = "账号密码错误";

		/**
		 *  ITCode authentication error
		 */
		public static final String ITCODE_AUTHENTICATION_ERROR = "ITCode认证失败";

		/**
		 * parse file error
		 */
		public static final String PARSE_FILE_ERROR = "解析文件失败";


	}

}
