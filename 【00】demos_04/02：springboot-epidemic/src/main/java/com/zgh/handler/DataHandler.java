package com.zgh.handler;

import com.google.gson.Gson;
import com.zgh.bean.DataBean;
import com.zgh.service.DataService;
import com.zgh.util.HttpClientUtil;
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
public class DataHandler {

    @Autowired
    private DataService service;

    /**
     * @PostConstruct
     * 修饰的方法 会在服务器启动时执行一次 且只执行一次
     *
     * 数据初始化
     */
    @PostConstruct
    public void saveData() {

        System.out.println("初始化数据的存储 - 表格");

        // 1.爬取疫情数据
        List<DataBean> dataBeans = getData();

        // 2.清空表中数据
        service.remove(null);

        // 3.数据存储
        service.saveBatch(dataBeans);

    }

    private static final SimpleDateFormat dateformet = new SimpleDateFormat("HH:mm:ss");

    /**
     * @Scheduled
     * 支持 cron 表达式
     *
     * 定时更新数据 每十分钟更新一次
     */
    @Scheduled(cron = "0 0/10 * * * ?")
    public void updateData() {
        System.out.println("更新数据存储 - 表格, 当前时间 " + dateformet.format(new Date()));
        List<DataBean> dataBeans = getData();
        service.remove(null);
        service.saveBatch(dataBeans);
    }

    // 新型冠状病毒肺炎 - 疫情实时追踪 from 腾讯新闻
    // 文件格式: JSON
    public static String urlStr = "https://view.inews.qq.com/g2/getOnsInfo?name=disease_h5";

    public static List<DataBean> getData() {

        String str = HttpClientUtil.doGet(urlStr);

        Gson gson = new Gson();
        Map map = gson.fromJson(str, Map.class);

        String dataStr = (String) map.get("data");
        Map data = gson.fromJson(dataStr, Map.class);

        ArrayList areaList = (ArrayList) data.get("areaTree");
        Map dataMap = (Map) areaList.get(0);
        ArrayList childrenList = (ArrayList) dataMap.get("children");

        ArrayList<DataBean> result = new ArrayList<>();
        for (int i = 0; i < childrenList.size(); i++) {

            Map temp = (Map) childrenList.get(i);

            String name = (String) temp.get("name");

            Map totalMap = (Map) temp.get("total");

            double nowConfirm = (double) totalMap.get("nowConfirm");
            double confirm = (double) totalMap.get("confirm");
            double dead = (double) totalMap.get("dead");
            double heal = (double) totalMap.get("heal");

            DataBean dataBean = new DataBean(null, name, (int) nowConfirm, (int) confirm, (int) dead, (int) heal);
            result.add(dataBean);

        }

        return result;

    }

}
