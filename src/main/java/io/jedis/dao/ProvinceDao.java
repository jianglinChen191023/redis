package io.jedis.dao;


import io.jedis.domain.Province;

import java.util.List;

/**
 * @author jianglinchen
 * @description 省份 持久层接口
 * @date 2020/4/9 / 15:40
 */
public interface ProvinceDao {
    List<Province> findAll();
}
