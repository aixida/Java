package com.zgh.handler;

import com.google.gson.Gson;
import com.zgh.bean.DataBean;
import com.zgh.util.HttpConnUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataHandler {

    // 新型冠状病毒肺炎 - 疫情实时追踪 from 腾讯新闻
    // 文件格式: JSON
    public static String urlStr = "https://view.inews.qq.com/g2/getOnsInfo?name=disease_h5";

    public static List<DataBean> getData() {

        String str = HttpConnUtil.doGet(urlStr);

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

            DataBean dataBean = new DataBean(name, (int) nowConfirm, (int) confirm, (int) dead, (int) heal);
            result.add(dataBean);

        }

        return result;

    }

}
