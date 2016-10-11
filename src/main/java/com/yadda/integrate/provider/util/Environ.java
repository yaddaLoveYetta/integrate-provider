package com.yadda.integrate.provider.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public final class Environ implements ApplicationContextAware {

	private static ApplicationContext CONTEXT = null;

	public static ApplicationContext getApplicationContext() {
		return CONTEXT;
	}

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		Environ.CONTEXT = applicationContext;
	}

	public static Object getBean(String name) {

		try {
			return CONTEXT.getBean(name);
		} catch (BeansException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public static <T> T getBean(Class<T> clazz) {

		try {
			return CONTEXT.getBean(clazz);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public static <T> T getBean(String name, Class<T> clazz) {

		try {
			return CONTEXT.getBean(name, clazz);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage(), e);
		}
	}

}
