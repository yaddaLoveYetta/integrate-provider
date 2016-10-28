
package com.yadda.integrate.provider.Exception;

/**
 * @description 业务异常
 * @author yadda
 * @email silenceisok@163.com
 * @time 2016年10月28日 上午11:14:02
 */
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 5444066173316422373L;

	private int errCode;

	public BusinessException(int errCode) {
		super();
		this.errCode = errCode;
	}

	public BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int errCode) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.errCode = errCode;
	}

	public BusinessException(String message, Throwable cause, int errCode) {
		super(message, cause);
		this.errCode = errCode;

	}

	public BusinessException(String message, int errCode) {
		super(message);
		this.errCode = errCode;

	}

	public BusinessException(Throwable cause, int errCode) {
		super(cause);
		this.errCode = errCode;

	}

	@Override
	public String getMessage() {

		String prefixStr = "抱歉，";
		String suffixStr = " 请稍后再试或与管理员联系！";
		String errCode = "" + this.errCode;

		StringBuffer msg = new StringBuffer("");

		msg.append(prefixStr);
		msg.append(super.getMessage());
		msg.append(errCode);
		msg.append(suffixStr);

		return msg.toString();

	}
}
