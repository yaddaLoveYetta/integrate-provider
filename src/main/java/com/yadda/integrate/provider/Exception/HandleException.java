
package com.yadda.integrate.provider.Exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * @description mvc异常统一处理
 * @author yadda
 * @email silenceisok@163.com
 * @time 2016年10月28日 上午9:03:14
 */
public class HandleException implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

		String className = ex.getClass().getSimpleName();

		switch (className) {
		case "BusinessLogicRunTimeException":

			break;

		default:
			break;
		}
		return null;
	}

}
