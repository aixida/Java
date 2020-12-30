package com.zgh.handler;

import com.google.gson.Gson;
import com.zgh.bean.DataBean;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JsoupHandler {

    // 新型冠状病毒肺炎 - 疫情实时追踪 from 丁香医生
    // 文件格式: Html
    public static String urlStr = "https://ncov.dxy.cn/ncovh5/view/pneumonia";

    public static List<DataBean> getData(){

        // String url = HttpConnUtil.doGet(urlStr);
        // Document doc = Jsoup.parse(url);

        Document doc;
        String data = null;
        try {
            doc = Jsoup.connect(urlStr).get();
            Element script = doc.getElementById("getAreaStat");
            data = script.data();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String subData = data.substring(data.indexOf("["), data.lastIndexOf("]") + 1);

        Gson gson = new Gson();
        ArrayList list = gson.fromJson(subData, ArrayList.class);

        ArrayList<DataBean> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Map map = (Map) list.get(i);
            String name = (String) map.get("provinceName");
            double nowConfirm = (double) map.get("currentConfirmedCount");
            double confirm = (double) map.get("confirmedCount");
            double dead = (double) map.get("deadCount");
            double heal = (double) map.get("curedCount");

            DataBean dataBean = new DataBean(name, (int) nowConfirm, (int) confirm, (int) dead, (int) heal);
            result.add(dataBean);
        }

        return result;

    }

}
