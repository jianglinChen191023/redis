package io.jedis.dao.impl;

import io.jedis.dao.ProvinceDao;
import io.jedis.domain.Province;
import io.jedis.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @author jianglinchen
 * @description 省份 持久层实现
 * @date 2020/4/9 / 15:42
 */
public class ProvinceDaoImpl implements ProvinceDao {

    // 1. 声明成员变量 jdbctemplement
    JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<Province> findAll() {
        // 1. 定义sql
        String sql = "select * from province";
        // 2. 执行SQL
        List<Province> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Province>(Province.class));

        return list;
    }


}
