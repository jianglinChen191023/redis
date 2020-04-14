package io.jedis.service;


import io.jedis.domain.Province;

import java.util.List;

/**
 * @author jianglinchen
 * @description 省份 服务接口
 * @date 2020/4/9 / 16:00
 */
public interface ProvinceService {
    List<Province> findAll();

    String findAllJson();
}
