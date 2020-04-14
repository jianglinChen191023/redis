package io.jedis.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jedis.domain.Province;
import io.jedis.service.ProvinceService;
import io.jedis.service.impl.ProvinceServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author jianglinchen
 * @description 省份 控制层
 * @date 2020/4/9 / 16:22
 */
@Controller
@RequestMapping("/provinceServlet")
public class ProvinceController {

    @RequestMapping(path = "/getData")
    public void getData(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 调用service查询
        ProvinceService provinceService = new ProvinceServiceImpl();
        List<Province> list = provinceService.findAll();

        // 序列化为json
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(list);

        // 返回
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(json);
    }

    @RequestMapping(path = "/getDataOptin")
    public void getDataOptin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 调用service查询
        ProvinceService provinceService = new ProvinceServiceImpl();
        String json = provinceService.findAllJson();

        // 返回
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(json);
    }

}
