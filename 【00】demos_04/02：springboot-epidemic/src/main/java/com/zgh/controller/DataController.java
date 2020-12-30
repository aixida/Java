package com.zgh.controller;

import com.zgh.bean.DataBean;
import com.zgh.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DataController {

    @Autowired
    DataService dataService;

    @GetMapping("/")
    public String list(Model model) {

        List<DataBean> dataList = dataService.list(); // mybatis-plus 查询全表数据
        model.addAttribute("dataList", dataList);
        return "list";

    }

}
