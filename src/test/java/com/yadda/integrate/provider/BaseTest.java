package com.yadda.integrate.provider;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试用例超类-主要是初始化spring环境
 * 
 * @description PagehelperTest.java
 * @author yadda
 * @email silenceisok@163.com
 * @time 2016年9月29日 下午5:36:40
 */
public class BaseTest {

	public static ApplicationContext ctx = null;

	/**
	 * @Description: 类加载时候执行，还未创建示例，必须是静态
	 * @Author:yadda
	 */
	@BeforeClass
	public static void before() {
		ctx = new ClassPathXmlApplicationContext("classpath:/config/applicationContext.xml");
	}

	/**
	 * 
	 * @Description: 每个方法之前执行
	 * @Author:yadda
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		System.out.println("一个测试方法开始。。");
	}

	/**
	 * 
	 * @Description: 每个方法之后执行
	 * @Author:yadda
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		System.out.println("一个测试方法结束");
	}

}
