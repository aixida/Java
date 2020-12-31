package com.zgh.controller;

import com.google.gson.Gson;
import com.zgh.bean.DataBean;
import com.zgh.bean.GraphBarBean;
import com.zgh.bean.GraphBean;
import com.zgh.bean.MapBean;
import com.zgh.handler.GraphHandler;
import com.zgh.service.DataService;
import com.zgh.service.GraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class GraphController {

    @Autowired
    GraphService graphService;

    @Autowired
    DataService dataService;

    @GetMapping("/graph")
    public String graph(Model model) {

        List<GraphBean> graphList = graphService.list(); // mybatis-plus 查询全表数据

        ArrayList<String> dateList = new ArrayList<>();
        ArrayList<Integer> confirmList = new ArrayList<>();
        ArrayList<Integer> healList = new ArrayList<>();
        ArrayList<Integer> deadList = new ArrayList<>();

        for (int i = 0; i < graphList.size(); i++) {

            GraphBean bean = graphList.get(i);
            dateList.add(bean.getDate());
            confirmList.add(bean.getConfirm());
            healList.add(bean.getHeal());
            deadList.add(bean.getDead());

        }

        model.addAttribute("dateList", new Gson().toJson(dateList));
        model.addAttribute("confirmList", new Gson().toJson(confirmList));
        model.addAttribute("healList", new Gson().toJson(healList));
        model.addAttribute("deadList", new Gson().toJson(deadList));

        return "graph";

    }

    @GetMapping("/graphBar")
    public String graphBar(Model model) {

        List<GraphBarBean> lists = GraphHandler.getImportData();
        Collections.sort(lists);

        ArrayList<String> nameList = new ArrayList<>();
        ArrayList<Integer> fromAbroadList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            GraphBarBean bean = lists.get(i);
            nameList.add(bean.getName());
            fromAbroadList.add(bean.getFromAbroad());
        }

        model.addAttribute("nameList", new Gson().toJson(nameList));
        model.addAttribute("fromAbroadList", new Gson().toJson(fromAbroadList));

        return "graphBar";
    }

    // 展示国内疫情地图
    @GetMapping("/map")
    public String map(Model model) {

        List<DataBean> dataList = dataService.list(); // mybatis-plus 查询全表数据

        List<MapBean> confirmList = new ArrayList<>();
        List<MapBean> nowConfirmList = new ArrayList<>();

        for (int i = 0; i < dataList.size(); i++) {

            DataBean data = dataList.get(i);

            MapBean bean1 = new MapBean(data.getName(), data.getConfirm());
            confirmList.add(bean1);

            MapBean bean2 = new MapBean(data.getName(), data.getNowConfirm());
            nowConfirmList.add(bean2);

        }

        model.addAttribute("confirmList", new Gson().toJson(confirmList));
        model.addAttribute("nowConfirmList", new Gson().toJson(nowConfirmList));

        return "map";

    }

}
