
package com.yadda.integrate.provider.cache;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisConnectionException;

/**
 * @description TODO(用一句话描述该文件做什么)
 * @author yadda
 * @email silenceisok@163.com
 * @time 2016年10月13日 下午5:24:33
 */
public class CachePool {

	JedisPool pool;
	private static final CachePool cachePool = new CachePool();
	private RedisUtil readisUtils = new RedisUtil();

	/** 单例模式 */
	public static CachePool getInstance() {
		return cachePool;
	}

	/** 初始化 */
	private CachePool() {
		pool = readisUtils.getjedisPool();
	}

	public Jedis getJedis() {
		Jedis jedis = null;
		boolean borrowOrOprSuccess = true;
		try {
			jedis = pool.getResource();
		} catch (JedisConnectionException e) {
			borrowOrOprSuccess = false;
			if (jedis != null)
				pool.returnBrokenResource(jedis);
		} finally {
			if (borrowOrOprSuccess)
				pool.returnResource(jedis);
		}
		jedis = pool.getResource();
		return jedis;
	}

	public JedisPool getJedisPool() {
		return this.pool;
	}
}
