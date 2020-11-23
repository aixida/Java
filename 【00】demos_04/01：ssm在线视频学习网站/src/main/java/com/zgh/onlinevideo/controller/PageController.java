package com.zgh.onlinevideo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    // 首页
    @RequestMapping("/")
    public String indexPage() {
        return "index";
    }

}
