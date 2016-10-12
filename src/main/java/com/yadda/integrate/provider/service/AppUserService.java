package com.yadda.integrate.provider.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yadda.integrate.api.IAppUserService;
import com.yadda.integrate.model.AppUser;
import com.yadda.integrate.model.AppUserExample;
import com.yadda.integrate.provider.annotation.ServiceLog;
import com.yadda.integrate.provider.dao.AppUserMapper;

public class AppUserService implements IAppUserService {

	private static Log log = LogFactory.getLog(AppUserService.class);

	@Resource
	private SqlSession sqlSession;

	public SqlSession getSqlSession() {
		return sqlSession;
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@ServiceLog(description = "根据id获取用户")
	public AppUser getAppUserById(Integer id) {

		if (log.isDebugEnabled()) {
			log.debug("enter getAppUserById");
		}

		AppUserMapper appUserMapper = sqlSession.getMapper(AppUserMapper.class);

		AppUser user = appUserMapper.selectByPrimaryKey(id);

		return user;

	}

	@ServiceLog(description = "分页获取用户")
	public List<AppUser> getAllAppUser() {

		if (log.isDebugEnabled()) {
			log.debug("enter getAllAppUser");
		}

		AppUserMapper appUserMapper = sqlSession.getMapper(AppUserMapper.class);
		AppUserExample example = new AppUserExample();

		PageHelper.startPage(1, 10); // 分页
		List<AppUser> list = appUserMapper.selectByExample(example);

		System.out.println(((Page<AppUser>) list).getTotal());

		return list;
	}

	public AppUser getAppUserByNumber(String number) {

		if (log.isDebugEnabled()) {
			log.debug("enter getAppUserByNumber");
		}

		AppUserMapper appUserMapper = sqlSession.getMapper(AppUserMapper.class);

		AppUserExample example = new AppUserExample();

		AppUserExample.Criteria criteria = example.createCriteria();

		criteria.andFnumberEqualTo("18617092729");

		List<AppUser> users = appUserMapper.selectByExample(example);

		if (users != null && !users.isEmpty()) {
			return users.get(0);
		}

		System.out.println(users.size());

		return null;
	}

}
