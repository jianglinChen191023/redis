package io.jedis.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jedis.dao.ProvinceDao;
import io.jedis.dao.impl.ProvinceDaoImpl;
import io.jedis.domain.Province;
import io.jedis.service.ProvinceService;
import io.jedis.util.JedisPoolUtils;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * @author jianglinchen
 * @description 省份 服务实现层
 * @date 2020/4/9 / 16:01
 */

public class ProvinceServiceImpl implements ProvinceService {

    ProvinceDao provinceDao = new ProvinceDaoImpl();

    @Override
    public List<Province> findAll() {
        return provinceDao.findAll();
    }

    @Override
    public String findAllJson() {
        // 先从redis中查询数据
        // 获取jedis客户端连接
        Jedis jedis = JedisPoolUtils.getJedis();
        String province_json = jedis.get("province");
        if (null != province_json && province_json.length() != 0) {
            System.out.println("redis中有数据,查询了缓存...");
        } else {
            System.out.println("redis中没有数据,查询数据库");
            // 从数据库中查询
            List<Province> list = provinceDao.findAll();
            // 序列化为json
            ObjectMapper mapper = new ObjectMapper();
            try {
                province_json = mapper.writeValueAsString(list);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            // 将数据存入jedis中
            jedis.set("province", province_json);
            // 归还连接
            jedis.close();
        }

        return province_json;
    }
}
