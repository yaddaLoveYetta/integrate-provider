
package com.yadda.integrate.provider.log;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
//import com.model.Log;
//import com.model.User;
//import com.service.LogService;
//import com.util.DateUtil;
//import com.util.JSONUtil;
//import com.util.SpringContextHolder;
//import com.util.WebConstants;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.yadda.integrate.provider.annotation.ServiceLog;

/**
 * @description 系统日志处理切点类
 * @author yadda
 * @email silenceisok@163.com
 * @time 2016年10月11日 下午2:18:37
 */

@Aspect
@Component
public class SystemLogAspect {

	// 本地异常日志记录对象
	private static Log logger = LogFactory.getLog(SystemLogAspect.class);

	// Service层切点
	@Pointcut("@annotation(com.yadda.integrate.provider.annotation.ServiceLog)")
	public void serviceAspect() {
	}

	/**
	 * 前置通知
	 */
	@Before("serviceAspect()")
	public void doBefore(JoinPoint joinPoint) {

		try {

			if (logger.isDebugEnabled()) {
				logger.debug("请求方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
				logger.debug("方法描述:" + getMethodDescription(joinPoint));
				logger.debug("请求参数:" + getMethodParameters(joinPoint));
			}

		} catch (Exception e) {
			// 记录本地异常日志
			logger.error("==前置通知异常==");
		}
	}

	/**
	 * 异常通知 用于拦截service层记录异常日志
	 * 
	 * @param joinPoint
	 * @param e
	 */
	@AfterThrowing(pointcut = "serviceAspect()", throwing = "e")
	public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {

		try {
			logger.debug("请求方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
			logger.debug("方法描述:" + getMethodDescription(joinPoint));
			logger.debug("请求参数:" + getMethodParameters(joinPoint));
		} catch (Exception ex) {
			// 记录本地异常日志
			logger.error("==异常通知异常==");
		}

	}

	/**
	 * 获取注解中对方法的描述信息 用于service层注解
	 * 
	 * @param joinPoint
	 *            切点
	 * @return 方法描述
	 * @throws Exception
	 */
	public static String getServiceMthodDescription(JoinPoint joinPoint) throws Exception {

		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();

		Object[] arguments = joinPoint.getArgs();
		Class targetClass = Class.forName(targetName);

		Method[] methods = targetClass.getMethods();
		String description = "";
		for (Method method : methods) {

			if (method.getName().equals(methodName)) {
				Class[] clazzs = method.getParameterTypes();

				Parameter[] parameters = method.getParameters();
				parameters[0].isNamePresent();
				parameters[0].getName();
				System.out.println(parameters.getClass().getSimpleName());

				for (int i = 0; i < clazzs.length; i++) {
					System.out.println(parameters[i].getName());
				}
				if (clazzs.length == arguments.length) {
					description = method.getAnnotation(ServiceLog.class).description();
					break;
				}
			}
		}
		return description;
	}

	private static String getMethodDescription(JoinPoint joinPoint) throws ClassNotFoundException {

		String description = "";
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();

		Class targetClass = Class.forName(targetName);

		Method[] methods = targetClass.getMethods();

		for (Method method : methods) {

			if (method.getName().equals(methodName)) {
				Class[] clazzs = method.getParameterTypes();
				description = method.getAnnotation(ServiceLog.class).description();
			}
		}
		return description;
	}

	private static String getMethodParameters(JoinPoint joinPoint) throws ClassNotFoundException {

		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();

		Object[] arguments = joinPoint.getArgs(); // 参数

		Class targetClass = Class.forName(targetName);

		Method[] methods = targetClass.getMethods();

		List<Map<String, Object>> params = new ArrayList<Map<String, Object>>();

		for (Method method : methods) {

			if (method.getName().equals(methodName)) {

				Class[] clazzs = method.getParameterTypes();

				for (int i = 0; i < clazzs.length; i++) {

					Parameter[] parameters = method.getParameters();

					Map<String, Object> item = new HashMap<String, Object>();
					item.put("name", parameters[i].getName());
					item.put("type", parameters[i].getType().getSimpleName());
					item.put("value", arguments[i]);

					params.add(item);
				}
			}
		}

		if (!params.isEmpty()) {
			return JSONObject.toJSONString(params);
		}

		return "{}";

	}
}