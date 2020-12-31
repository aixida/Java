package com.zgh.handler;

import com.google.gson.Gson;
import com.zgh.bean.GraphBarBean;
import com.zgh.bean.GraphBean;
import com.zgh.service.GraphService;
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
    // 爬取内容: 自疫情爆发以来国内每日 累计确诊人数 confirm、死亡人数 dead、治愈人数 heal
    // 前端展示: 折线图
    public static String urlStr = "https://api.inews.qq.com/newsqa/v1/query/inner/publish/modules/list?modules=chinaDayList,chinaDayAddList,cityStatis,nowConfirmStatis,provinceCompare";

    public static List<GraphBean> getData() {

        String str = HttpClientUtil.doGet(urlStr);

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

            GraphBean graphBean = new GraphBean(null, date, (int) confirm, (int) heal, (int) dead);
            result.add(graphBean);

        }

        return result;

    }

    // 新型冠状病毒肺炎 - 疫情实时追踪 from 腾讯新闻
    // 文件格式: JSON
    // 爬取内容: 省市境外输入
    // 前端展示: 柱状图
    // 注意: 这部分内容就不经过数据库了呀
    public static String urlStr2 = "https://view.inews.qq.com/g2/getOnsInfo?name=disease_h5";

    public static List<GraphBarBean> getImportData() {

        String str = HttpClientUtil.doGet(urlStr2);

        Gson gson = new Gson();
        Map map = gson.fromJson(str, Map.class);

        String dataStr = (String) map.get("data");
        Map data = gson.fromJson(dataStr, Map.class);

        ArrayList areaList = (ArrayList) data.get("areaTree");
        Map dataMap = (Map) areaList.get(0);
        ArrayList childrenList = (ArrayList) dataMap.get("children");

        ArrayList<GraphBarBean> result = new ArrayList<>();
        for (int i = 0; i < childrenList.size(); i++) {

            Map temp = (Map) childrenList.get(i);

            String name = (String) temp.get("name");

            ArrayList children = (ArrayList) temp.get("children");
            for (int j = 0; j < children.size(); j++) {
                Map importMap = (Map) children.get(j);
                if ("境外输入".equals(importMap.get("name"))){
                    Map importTotalMap = (Map) importMap.get("total");
                    double confirm = (double) importTotalMap.get("confirm");

                    GraphBarBean bean = new GraphBarBean(name, (int) confirm);
                    result.add(bean);
                }
             }
        }

        return result;

    }

}
