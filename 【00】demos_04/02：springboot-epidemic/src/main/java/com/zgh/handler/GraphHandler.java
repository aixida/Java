package com.zgh.handler;

import com.google.gson.Gson;
import com.zgh.bean.GraphBean;
import com.zgh.service.GraphService;
import com.zgh.util.HttpConnUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
public class GraphHandler {

    @Autowired
    GraphService service;

    // 初始化数据
    @PostConstruct
    public void saveData() {
        System.out.println("初始化数据的存储 - 折线图");

        List<GraphBean> graphBeans = getData();
        service.remove(null);
        service.saveBatch(graphBeans);
    }

    private static final SimpleDateFormat dateformet = new SimpleDateFormat("HH:mm:ss");

    // 定时更新数据 每天中午12点触发
    @Scheduled(cron = "0 0 12 * * ?")
    public void updateData() {
        System.out.println("更新数据存储 - 折线图, 当前时间 " + dateformet.format(new Date()));
        List<GraphBean> graphBeans = getData();
        service.remove(null);
        service.saveBatch(graphBeans);
    }


    // 新型冠状病毒肺炎 - 疫情实时追踪 from 腾讯新闻
    // 文件格式: JSON
    public static String urlStr = "https://api.inews.qq.com/newsqa/v1/query/inner/publish/modules/list?modules=chinaDayList,chinaDayAddList,cityStatis,nowConfirmStatis,provinceCompare";

    public static List<GraphBean> getData() {

        String str = HttpConnUtil.doGet(urlStr);

        Gson gson = new Gson();
        Map map = gson.fromJson(str, Map.class);

        Map data = (Map) map.get("data");
        ArrayList dayList = (ArrayList) data.get("chinaDayList");

        ArrayList<GraphBean> result = new ArrayList<>();
        for (int i = 0; i < dayList.size(); i++) {

            Map tmp = (Map) dayList.get(i);

            String date = (String) tmp.get("date");
            double confirm = (double) tmp.get("confirm");
            double dead = (double) tmp.get("dead");
            double heal = (double) tmp.get("heal");

            GraphBean graphBean = new GraphBean(date, (int) confirm, (int) heal, (int) dead);
            result.add(graphBean);

        }

        return result;

    }


}
