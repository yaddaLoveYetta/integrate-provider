package com.yadda.integrate.provider;

import org.junit.Assert;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yadda.integrate.api.IAppUserService;
import com.yadda.integrate.model.AppUser;
import com.yadda.integrate.provider.util.Environ;

public class Main {

	public static void main(String[] args) {

		ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:/config/applicationContext.xml");

		IAppUserService appUserService = Environ.getBean(IAppUserService.class);

		AppUser user = appUserService.getAppUserById(1);

		System.out.println(user.getFuserid());

		Assert.assertEquals(user.getFuserid().intValue(), 1);
	}
}
