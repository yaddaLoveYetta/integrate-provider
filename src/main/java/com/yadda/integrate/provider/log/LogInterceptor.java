
package com.yadda.integrate.provider.log;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yadda.integrate.provider.annotation.DoLog;

/**
 * @description 通用日志拦截器
 * @author yadda
 * @email silenceisok@163.com
 * @time 2016年10月28日 下午1:59:34
 */
public class LogInterceptor implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {

		Log log = LogFactory.getLog(invocation.getThis().getClass());

		if (log.isDebugEnabled()) {

			Method reflectMethod = invocation.getMethod();

			Method realMethod = invocation.getThis().getClass().getMethod(reflectMethod.getName(), reflectMethod.getParameterTypes());

			String description = "";

			if (realMethod.isAnnotationPresent(DoLog.class)) {
				description = realMethod.getAnnotation(DoLog.class).description().trim(); // 方法上的描述
			}

			Parameter[] parameters = reflectMethod.getParameters(); // 方法参数名

			Object[] arguments = invocation.getArguments(); // 方法参数值

			Map<Object, Object> args = new HashMap<Object, Object>();

			if (parameters.length == arguments.length) {

				for (int i = 0; i < parameters.length; i++) {

					args.put(parameters[i].getName() + "[" + parameters[i].getParameterizedType().getTypeName() + "]", arguments[i]);
				}

			}

			log.debug(">>>>>> method:" + realMethod.getName() + (description.isEmpty() ? "" : ":" + description) + " >>>>>>> Enter >>>>>>>");
			log.debug(">>>>>> args:" + args.toString());
		}

		Object obj = invocation.proceed();// 执行调用链

		// log.debug(">>>>>> 方法:" + realMethod + ">>>>>>> END >>>>>>");
		//
		// log.debug(">>>>>> 返回:" + obj.toString() + ">>>>>>> END >>>>>>");

		return obj;
	}

}
