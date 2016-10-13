
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
		logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>MybatisRedisCache:id=" + id);
		this.id = id;
	}

	public String getId() {
		return this.id;
	}

	public void putObject(Object key, Object value) {
		logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>putObject:" + key + "=" + value);
		try {
			RedisUtil.getJedis().set(SerializeUtil.serialize(key.toString()), SerializeUtil.serialize(value));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Object getObject(Object key) {
		Object value = null;
		try {
			value = SerializeUtil.unSerialize(RedisUtil.getJedis().get(SerializeUtil.serialize(key.toString())));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>getObject:" + key + "=" + value);
		return value;
	}

	public Object removeObject(Object key) {
		try {
			return RedisUtil.getJedis().expire(SerializeUtil.serialize(key.toString()), 0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void clear() {
		RedisUtil.getJedis().flushDB();
	}

	public int getSize() {
		return Integer.valueOf(RedisUtil.getJedis().dbSize().toString());
	}

	public ReadWriteLock getReadWriteLock() {
		return readWriteLock;
	}

}
