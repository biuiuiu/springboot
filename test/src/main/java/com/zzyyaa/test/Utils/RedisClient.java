package com.zzyyaa.test.Utils;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;

@Component
public class RedisClient {
	
	@Autowired
	private RedisUtils redisUtils;
	
	/** 
     * 通过key删除（字节） 
     * @param key 
     */  
	public void del(byte[] key) {
		Jedis jedis = redisUtils.getInstance();//获取实例
		jedis.del(key);
		redisUtils.returnResource(jedis);//操作完关闭连接
	}
	/** 
     * 通过key删除 
     * @param key 
     */  
    public void del(String key){  
        Jedis jedis = redisUtils.getInstance();  
        jedis.del(key);  
        redisUtils.returnResource(jedis);  
    }  
    /** 
     * 添加key value 并且设置存活时间(byte) 
     * @param key 
     * @param value 
     * @param liveTime 
     */  
    public void set(byte [] key,byte [] value,int liveTime){  
        Jedis jedis = redisUtils.getInstance();  
        jedis.set(key, value);  
        jedis.expire(key, liveTime);  
        redisUtils.returnResource(jedis);  
    }  
    /** 
     * 添加key value 并且设置存活时间 
     * @param key 
     * @param value 
     * @param liveTime 
     */  
    public void set(String key,String value,int liveTime){  
        Jedis jedis = redisUtils.getInstance();  
        jedis.set(key, value);  
        jedis.expire(key, liveTime);  
        redisUtils.returnResource(jedis);  
    }  
    /** 
     * 添加key value 
     * @param key 
     * @param value 
     */  
    public void set(String key,String value){  
        Jedis jedis = redisUtils.getInstance();  
        jedis.set(key, value);  
        redisUtils.returnResource(jedis);  
    }  
    /**添加key value (字节)(序列化) 
     * @param key 
     * @param value 
     */  
    public void set(byte [] key,byte [] value){  
        Jedis jedis = redisUtils.getInstance();  
        jedis.set(key, value);  
        redisUtils.returnResource(jedis);  
    }  
    /** 
     * 获取redis value (String) 
     * @param key 
     * @return 
     */  
    public String get(String key){  
        Jedis jedis = redisUtils.getInstance();  
         String value = jedis.get(key);  
        redisUtils.returnResource(jedis);  
        return value;  
    }  
    /** 
     * 获取redis value (byte [] )(反序列化) 
     * @param key 
     * @return 
     */  
    public byte[] get(byte [] key){  
        Jedis jedis = redisUtils.getInstance();  
        byte[] value = jedis.get(key);  
        redisUtils.returnResource(jedis);  
        return value;  
    }  
  
    /** 
     * 通过正则匹配keys 
     * @param pattern 
     * @return 
     */  
    public Set<String> keys(String pattern){  
        Jedis jedis = redisUtils.getInstance();  
        Set<String> value = jedis.keys(pattern);  
        redisUtils.returnResource(jedis);  
        return value;  
    }  
  
    /** 
     * 检查key是否已经存在 
     * @param key 
     * @return 
     */  
    public boolean exists(String key){  
        Jedis jedis = redisUtils.getInstance();  
        boolean value = jedis.exists(key);  
        redisUtils.returnResource(jedis);  
        return value;  
    }  
      
    /*******************redis list操作************************/  
    /** 
     * 往list中添加元素 
     * @param key 
     * @param value 
     */  
    public void lpush(String key,String value){  
        Jedis jedis = redisUtils.getInstance();  
        jedis.lpush(key, value);  
        redisUtils.returnResource(jedis);  
    }  
      
    public void rpush(String key,String value){  
        Jedis jedis = redisUtils.getInstance();  
        jedis.rpush(key, value);  
        redisUtils.returnResource(jedis);  
    }  
      
    /** 
     * 数组长度 
     * @param key 
     * @return 
     */  
    public Long llen(String key){  
        Jedis jedis = redisUtils.getInstance();  
        Long len = jedis.llen(key);  
        redisUtils.returnResource(jedis);  
        return len;  
    }  
      
    /** 
     * 获取下标为index的value 
     * @param key 
     * @param index 
     * @return 
     */  
    public String lindex(String key,Long index){  
        Jedis jedis = redisUtils.getInstance();  
        String str = jedis.lindex(key, index);  
        redisUtils.returnResource(jedis);  
        return str;  
    }  
      
    public String lpop(String key){  
        Jedis jedis = redisUtils.getInstance();  
        String str = jedis.lpop(key);  
        redisUtils.returnResource(jedis);  
        return str;  
    }  
      
    public List<String> lrange(String key,long start,long end){  
        Jedis jedis = redisUtils.getInstance();  
        List<String> str = jedis.lrange(key, start, end);  
        redisUtils.returnResource(jedis);  
        return str;  
    }  
    /*********************redis list操作结束**************************/  
      
    /** 
     * 清空redis 所有数据 
     * @return 
     */  
    public String flushDB(){  
        Jedis jedis = redisUtils.getInstance();  
        String str = jedis.flushDB();  
        redisUtils.returnResource(jedis);  
        return str;  
    }  
    /** 
     * 查看redis里有多少数据 
     */  
    public long dbSize(){  
        Jedis jedis = redisUtils.getInstance();  
        long len = jedis.dbSize();  
        redisUtils.returnResource(jedis);  
        return len;  
    }  
    /** 
     * 检查是否连接成功 
     * @return 
     */  
    public String ping(){  
        Jedis jedis = redisUtils.getInstance();  
        String str = jedis.ping();  
        redisUtils.returnResource(jedis);  
        return str;  
    }  
}
