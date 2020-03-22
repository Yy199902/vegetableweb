package com.lanou.vegetableweb.utils;

import com.lanou.vegetableweb.config.redis.RedisPool;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
@Slf4j
public class RedisPoolUtil {
    //设置key的有效期
    public static Long expire(String key,int seconds){
        Jedis jedis = null;
        Long result = null;
        try {
            jedis = RedisPool.getJedis();//从自定义RedisPool连接池获取一个实例
            result = jedis.expire(key,seconds);
        } catch (Exception e) {
            log.error("expire key:{} error",key ,e);
            RedisPool.returnBrokenResource(jedis);//发生错误回收
            return result;
        }
        RedisPool.returnResource(jedis);//正常回收
        return result;
    }

    //设置key时间并且设置过期时间
    public static String setEx(String key,String value,int seconds){
        Jedis jedis = null;
        String result = null;
        try {
            jedis = RedisPool.getJedis();
            result = jedis.setex(key,seconds,value);
        } catch (Exception e) {
            log.error("expire key:{} value:{} error",key ,value,e);
            RedisPool.returnBrokenResource(jedis);
            return result;
        }
        RedisPool.returnResource(jedis);
        return result;
    }

    //设置一个key
    public static String set(String key,String value){
        Jedis jedis = null;
        String result = null;
        try {
            jedis = RedisPool.getJedis();
            result = jedis.set(key,value);
        } catch (Exception e) {
            log.error("expire key:{} value:{} error",key ,value,e);
            RedisPool.returnBrokenResource(jedis);
            return result;
        }
        RedisPool.returnResource(jedis);
        return result;
    }

    //获取一个key
    public static String get(String key){
        Jedis jedis = null;
        String result = null;
        try {
            jedis = RedisPool.getJedis();
            result = jedis.get(key);
        } catch (Exception e) {
            log.error("expire key:{} value:{} error",key ,e);
            RedisPool.returnBrokenResource(jedis);
            return result;
        }
        RedisPool.returnResource(jedis);
        return result;
    }

    //删除一个key
    public static Long del(String key){
        Jedis jedis = null;
        Long result = null;
        try {
            jedis = RedisPool.getJedis();
            result = jedis.del(key);
        } catch (Exception e) {
            log.error("expire key:{} value:{} error",key ,e);
            RedisPool.returnBrokenResource(jedis);
            return result;
        }
        RedisPool.returnResource(jedis);
        return result;
    }

}
