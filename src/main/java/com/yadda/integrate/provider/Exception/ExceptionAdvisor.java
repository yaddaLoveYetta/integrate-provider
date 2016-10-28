
package com.yadda.integrate.provider.Exception;

import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.dao.DataAccessException;

import com.yadda.integrate.provider.util.StatusCode;

/**
 * @description 系统异常统一处理
 * @author yadda
 * @email silenceisok@163.com
 * @time 2016年10月28日 上午10:52:05
 */
public class ExceptionAdvisor implements ThrowsAdvice {

	public void afterThrowing(Method method, Object[] args, Object target, Exception ex) {

		Log log = LogFactory.getLog(target.getClass());

		// 在后台中输出错误异常异常信息
		log.info("**************************************************************");
		log.info("Error happened in class: " + target.getClass().getName());
		log.info("Error happened in method: " + method.getName());
		for (int i = 0; i < args.length; i++) {
			log.info("arg[" + i + "]: " + args[i]);
		}
		log.info("Exception class: " + ex.getClass().getName());
		log.info("ex.getMessage():" + ex.getMessage());

		log.info("**************************************************************");

		// 在这里判断异常，根据不同的异常返回错误。
		if (ex.getClass().equals(DataAccessException.class)) {

			throw new BusinessException("数据库操作失败！", StatusCode.BUSINESS_LOGIC_ERROR);
		} else if (ex.getClass().toString().equals(NullPointerException.class.toString())) {

			throw new BusinessException("调用了未经初始化的对象或者是不存在的对象！", StatusCode.BUSINESS_LOGIC_ERROR);
		} else if (ex.getClass().equals(IOException.class)) {

			throw new BusinessException("IO异常！", StatusCode.BUSINESS_LOGIC_ERROR);
		} else if (ex.getClass().equals(ClassNotFoundException.class)) {

			throw new BusinessException("指定的类不存在！", StatusCode.BUSINESS_LOGIC_ERROR);
		} else if (ex.getClass().equals(ArithmeticException.class)) {

			throw new BusinessException("数学运算异常！", StatusCode.BUSINESS_LOGIC_ERROR);
		} else if (ex.getClass().equals(ArrayIndexOutOfBoundsException.class)) {

			throw new BusinessException("数组下标越界!", StatusCode.BUSINESS_LOGIC_ERROR);
		} else if (ex.getClass().equals(IllegalArgumentException.class)) {

			throw new BusinessException("方法的参数错误！", StatusCode.BUSINESS_LOGIC_ERROR);
		} else if (ex.getClass().equals(ClassCastException.class)) {

			throw new BusinessException("类型强制转换错误！", StatusCode.BUSINESS_LOGIC_ERROR);
		} else if (ex.getClass().equals(SecurityException.class)) {

			throw new BusinessException("违背安全原则异常！", StatusCode.BUSINESS_LOGIC_ERROR);
		} else if (ex.getClass().equals(SQLException.class)) {

			throw new BusinessException("操作数据库异常！", StatusCode.BUSINESS_LOGIC_ERROR);
		} else if (ex.getClass().equals(NoSuchMethodError.class)) {

			throw new BusinessException("方法末找到异常！", StatusCode.BUSINESS_LOGIC_ERROR);
		} else if (ex.getClass().equals(InternalError.class)) {

			throw new BusinessException("Java虚拟机发生了内部错误", StatusCode.BUSINESS_LOGIC_ERROR);
		} else {
			throw new BusinessException("程序内部错误，操作失败！" + ex.getMessage(), StatusCode.BUSINESS_LOGIC_ERROR);
		}
	}
}
