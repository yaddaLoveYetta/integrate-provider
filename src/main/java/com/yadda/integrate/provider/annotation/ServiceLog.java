
package com.yadda.integrate.provider.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description 服务日志注解
 * @author yadda
 * @email silenceisok@163.com
 * @time 2016年10月11日 下午2:11:49
 */
@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ServiceLog {
	/**
	 * 描述
	 * 
	 * @Author yadda
	 * @time 2016年10月11日 下午2:07:24
	 * @return
	 */
	String description() default "";
}
