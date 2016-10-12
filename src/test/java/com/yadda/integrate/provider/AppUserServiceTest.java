package com.yadda.integrate.provider;

import java.util.List;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.yadda.integrate.api.IAppUserService;
import com.yadda.integrate.model.AppUser;
import com.yadda.integrate.provider.util.Environ;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AppUserServiceTest extends BaseTest {

	@Test
	public void testGetAppUserById() {
		

		IAppUserService appUserService = Environ.getBean(IAppUserService.class);

		AppUser user = appUserService.getAppUserById(1);

		System.out.println(user.getFuserid());

		Assert.assertEquals(user.getFuserid().intValue(), 1);

	}

	@Test
	public void testGetAllAppUser() {

		IAppUserService appUserService = Environ.getBean(IAppUserService.class);

		List<AppUser> users = appUserService.getAllAppUser();

		System.out.println(users.size());

		Assert.assertEquals(users.size(), 10);
	}

	@Test
	public void testGetAppUserByNumber() {

		IAppUserService appUserService = Environ.getBean(IAppUserService.class);

		AppUser user = appUserService.getAppUserByNumber("18617092729");

		System.out.println(user);
		Assert.assertEquals(user.getFuserid().intValue(), 1);
	}

	@Test
	@Ignore // 胡洛此方法测试
	public void testGetSqlSession() {

	}

	@Test(expected = Exception.class) // 期待抛出一个异常
	public void testDivide() throws Exception {

		int a = 1 / 0;

		System.out.println(a);
	}
}
