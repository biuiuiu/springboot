package com.zzyyaa.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfig {
	
	@Autowired
	private Environment env;
	
	@Bean
	public JedisPool jedisPool(){
		try {
			System.out.println("1++"+env.getProperty("spring.redis.host"));
			int port = Integer.valueOf(env.getProperty("spring.redis.port"));
			String ip = env.getProperty("spring.redis.host");
			String password = env.getProperty("spring.redis.password");
			JedisPoolConfig config = new JedisPoolConfig();
			config.setMaxTotal(Integer.valueOf(env.getProperty("spring.redis.pool.max-active")));
			// // 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
			 config.setMaxIdle(Integer.valueOf(env.getProperty("spring.redis.pool.max-idle")));
			// // 等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
			 config.setMaxWaitMillis(Long.valueOf(env.getProperty("spring.redis.pool.max-wait")));
			// // 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
			 config.setTestOnBorrow(true);			
			 JedisPool jedisPool = new JedisPool(config, ip, port, 5000, password);// 连接池
			 return jedisPool;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
