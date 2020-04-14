package com.spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author jianglinchen
 * @description 测试MVC
 * @date 2020/4/10 / 16:42
 */
@Controller
@RequestMapping("/hello")
public class TestController {

    @GetMapping(path = "/world")
    public ModelAndView world() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("/world");
        return modelAndView;
    }


}
