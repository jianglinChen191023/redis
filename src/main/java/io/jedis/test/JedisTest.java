package io.jedis.test;

import io.jedis.util.JedisPoolUtils;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author jianglinchen
 * @description 入门jedis
 * @date 2020/4/8 / 16:42
 */
public class JedisTest {

    /**
     * 快速入门
     */
    @Test
    public void test1() {
        // 1. 获取连接
        Jedis jedis = new Jedis("localhost", 6379);

        // 2. 操作
        jedis.set("username", "zhangsan");

        // 3. 关闭连接
        jedis.close();
    }

    // 操作各种redis中的数据结构

    /**
     * 字符串类型
     * set
     * get
     */
    @Test
    public void test2() {
        // 1. 获取连接
        Jedis jedis = new Jedis(); // Connection默认: host = "localhost"; port = 6379;

        // 2. 操作
        jedis.set("username", "zhangsan");
        String s = jedis.get("username");
        System.out.println(s);

        // 可以使用setex()存储可以指定过期时间的 key value
        // 存储有时效的激活码、验证码
        jedis.setex("activecode", 20, "hehe");// 将activecode: hehe键值对 存入redis, 并且20秒后自动删除该键值对

        // 3. 关闭连接
        jedis.close();
    }

    /**
     * 哈希类型
     * hset
     * hget
     */
    @Test
    public void test3() {
        // 1. 获取连接
        Jedis jedis = new Jedis(); // Connection默认: host = "localhost"; port = 6379;

        // 2. 操作
        // 存储hash
        jedis.hset("user", "name", "lisi");
        jedis.hset("user", "age", "23");
        jedis.hset("user", "gender", "fmale");
        String s = jedis.hget("user", "name");
        System.out.println(s);

        // 获取hash的所有map中的数据
        Map<String, String> user = jedis.hgetAll("user");
        Set<String> strings = user.keySet();
        for (String key : strings) {
            // 获取value
            String value = user.get(key);
            System.out.println(key + ": " + value);
        }

        // 3. 关闭连接
        jedis.close();
    }

    /**
     * 列表类型
     */
    @Test
    public void test4() {
        // 1. 获取连接
        Jedis jedis = new Jedis();

        // 2. 操作
        // 删除
        jedis.del("mylist");

        // list存储
        // 从左边存
        jedis.lpush("mylist", "a", "b", "c");
        // 从右边存
        jedis.rpush("mylist", "a", "b", "c");

        // list范围获取
        List<String> mylist = jedis.lrange("mylist", 0, -1);
        System.out.println(mylist);

        // list 弹出
        String mylist1 = jedis.lpop("mylist");
        System.out.println(mylist1);

        String mylist2 = jedis.rpop("mylist");
        System.out.println(mylist2);

        // list范围获取
        List<String> mylist3 = jedis.lrange("mylist", 0, -1);
        System.out.println(mylist3);
        // 3. 关闭连接
        jedis.close();
    }

    /**
     * 集合类型
     */
    @Test
    public void test5() {
        // 1. 获取连接
        Jedis jedis = new Jedis(); // Connection默认: host = "localhost"; port = 6379;

        // 2. 操作

        // set存储
        jedis.sadd("myset", "java", "php", "c++");

        // set 获取
        Set<String> myset = jedis.smembers("myset");
        System.out.println(myset);

        // 3. 关闭连接
        jedis.close();
    }

    /**
     * sortedset 数据结构操作
     */
    @Test
    public void test6() {
        // 1. 获取连接
        Jedis jedis = new Jedis(); // Connection默认: host = "localhost"; port = 6379;

        // 2. 操作

        // sortedset 存储
        jedis.zadd("mysortedset", 266, "嫦娥");
        jedis.zadd("mysortedset", 3, "后裔");
        jedis.zadd("mysortedset", 300, "李白");

        // sortedset 获取
        Set<String> mysortedset = jedis.zrange("mysortedset", 0, -1);
        System.out.println(mysortedset);

        // 3. 关闭连接
        jedis.close();
    }

    /**
     * jedis 连接处的使用
     */
    @Test
    public void test7() {
        // 0. 创建一个配置对象
        JedisPoolConfig config = new JedisPoolConfig();
        // 最大允许的连接数
        config.setMaxTotal(50);
        // 最大的空闲连接
        config.setMaxIdle(10);

        // 1. 创建jedis连接池对象 , 对于jedis的连接更好的复用和管理
        JedisPool jedisPool = new JedisPool(config, "localhost", 6379);

        // 2. 获取连接
        Jedis jedis = jedisPool.getResource();

        // 3. 使用
        jedis.set("hehe2", "haha2");

        // 4. 归还连接池
        jedis.close();
    }

    /**
     * jedis连接池工具类获取
     */
    @Test
    public void test8() {
        // 通过连接池工具类获取
        Jedis jedis = JedisPoolUtils.getJedis();

        // 使用
        jedis.set("hello", "jedis");

        // 归还连接池
        jedis.close();
    }


}
