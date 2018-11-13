package com.niktech.console.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wangxinyu
 * @date 2018/11/12 13:22
 * @verson 1.0
 **/
@Controller
public class IndexController extends AbstractController{

    @RequestMapping("/index")
    public String index(Model model){
        model.addAttribute("username","ii");
        return "index";
    }

    @RequestMapping("/system/index/main")
    public String main(Model model){
        return "system/index/main";
    }

    @RequestMapping("/base/log/list")
    public String log(Model model){
        return "system/index/log";
    }
}
