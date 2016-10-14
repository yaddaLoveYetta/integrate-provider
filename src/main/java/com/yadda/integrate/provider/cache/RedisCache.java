
package com.yadda.integrate.provider.cache;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.cache.Cache;

/**
 * @description mybatis二级缓存redis实现
 * @author yadda
 * @email silenceisok@163.com
 * @time 2016年10月13日 下午4:32:36
 */
public class RedisCache implements Cache {

	private static Log logger = LogFactory.getLog(RedisCache.class);

	/** The ReadWriteLock. */
	private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

	private String id;

	public RedisCache(final String id) {
		if (id == null) {
			throw new IllegalArgumentException("Cache instances require an ID");
		}
		logger.debug(">>>>>>MybatisRedisCache:id=" + id);
		this.id = id;
	}

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public void putObject(Object key, Object value) {

		if (logger.isDebugEnabled()) {
			logger.debug(">>>>>>putObject>>key:" + key);
			logger.debug(">>>>>>putObject>>value:" + value);
		}
		try {

			RedisUtil.getJedis().set(SerializeUtil.serialize(key.toString()), SerializeUtil.serialize(value));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Object getObject(Object key) {

		Object value = null;

		try {
			value = SerializeUtil.unSerialize(RedisUtil.getJedis().get(SerializeUtil.serialize(key.toString())));
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (logger.isDebugEnabled()) {
			if (value != null) {
				logger.debug(">>>>>> getObject:cache hit");
				logger.debug(">>>>>> cache key:" + key);
				logger.debug(">>>>>> cache value:" + value);
			} else {
				logger.debug(">>>>>> getObject:cache miss");
				logger.debug(">>>>>> cache key:" + key);
			}
		}
		// clear();
		return value;
	}

	@Override
	public Object removeObject(Object key) {
		try {
			return RedisUtil.getJedis().expire(SerializeUtil.serialize(key.toString()), 0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void clear() {
		RedisUtil.getJedis().flushDB();
	}

	@Override
	public int getSize() {
		return Integer.valueOf(RedisUtil.getJedis().dbSize().toString());
	}

	@Override
	public ReadWriteLock getReadWriteLock() {
		return readWriteLock;
	}

}
