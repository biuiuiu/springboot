package com.zzyyaa.test.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component
public class RedisUtils {
	
	
	@Autowired
	private JedisPool jedisPool;
	
	/*private static JedisPool jedisPool;// jedis连接池
	private static String ip = "10.110.200.17";
	private static int port = 6385;
	private static int MAX_ACTIVE = 80;
	private static int MAX_IDLE = 8;
	private static int MAX_WAIT = 8;
	private static final String password = "6385pwd123";
	
	
	private static Environment env;
	
	public Environment getEnv() {
		return env;
	}

	public void setEnv(Environment env) {
		this.env = env;
	}

	static {
		try {
			System.out.println("1++"+env.getProperty("spring.redis.host"));
			JedisPoolConfig config = new JedisPoolConfig();
			// Redis最大实例数，若为-1，则不限制，默认为8个spring.redis.pool.max-idle
			config.setMaxTotal(MAX_ACTIVE);
			// 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
			config.setMaxIdle(MAX_IDLE);
			// 等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
			config.setMaxWaitMillis(MAX_WAIT);
			// 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
			config.setTestOnBorrow(true);
			jedisPool = new JedisPool(config, ip, port, 5000, password);// 连接池
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	// private static String ip;
	//
	// private static Environment env;
	//
	// private static JedisPool jedisPool;// jedis连接池
	//
	// /**
	// * 实现对静态变量注入
	// */
	// @Value("${spring.redis.host}")
	// private void setIp(String ip) {
	// System.out.println("3");
	// this.ip = ip;
	// }
	//
	// @Autowired
	// private Environment env1;
	//
	// /**
	// * 被@PostConstruct修饰的方法会在服务器加载Servlet的时候运行，
	// * 并且只会被服务器调用一次，类似于Serclet的inti()方法。
	// * 被@PostConstruct修饰的方法会在构造函数之后，init()方法之前运行。
	// */
	// @PostConstruct
	// public void init() {
	// this.env = env1;
	// System.out.println("2:"+env);
	// }
	// 静态块会最先执行，在给静态变量赋值之前
	// private static void initMethord(){
	// try {
	// System.out.println("1");
	// int port = Integer.valueOf(env.getProperty("spring.redis.port"));
	// JedisPoolConfig config = new JedisPoolConfig();
	// // Redis最大实例数，若为-1，则不限制，默认为8个spring.redis.pool.max-idle
	// config.setMaxTotal(Integer.valueOf(env.getProperty("spring.redis.pool.max-active")));
	// // 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
	// config.setMaxIdle(Integer.valueOf(env.getProperty("spring.redis.pool.max-idle")));
	// // 等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
	// config.setMaxWaitMillis(Long.valueOf(env.getProperty("spring.redis.pool.max-wait")));
	// // 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
	// config.setTestOnBorrow(true);
	// jedisPool = new JedisPool(config, ip, port);// 连接池
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }

	/**
	 * 获取Jedis实例
	 * 
	 * @return
	 */
	// 2017-12-22 调用报错，不能从连接池中获取资源。未解决
	public synchronized Jedis getInstance() {
		try {
//			if (jedisPool == null) {
//				initMethord();
//			}
			if (jedisPool != null) {
				Jedis jedis = jedisPool.getResource();
				return jedis;
			} else {
				return null;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 释放jedis资源
	 * 
	 * @param jedis
	 */
	@SuppressWarnings("deprecation")
	public void returnResource(final Jedis jedis) {
		if (jedis != null) {
			jedisPool.returnResource(jedis);
		}
	}

	/**
	 * 释放jedis资源
	 * 
	 * @param jedis
	 */
	@SuppressWarnings("deprecation")
	public void returnBrokenResource(final Jedis jedis) {
		if (jedis != null) {
			jedisPool.returnBrokenResource(jedis);
		}
	}
}
