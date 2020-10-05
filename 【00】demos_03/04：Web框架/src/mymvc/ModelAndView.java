package mymvc;

import java.util.HashMap;

/**
 * 存储Controller类想要带走的信息、请求转发或重定向信息
 */
public class ModelAndView {

    private String viewName;//路径资源名
    private HashMap<String, Object> attributeMap = new HashMap<>();//包装 request.setAttribute()

    //用户设置请求转发、重定向的路径资源名
    public void setViewName(String path) {
        viewName = path;
    }

    //用户存值
    public void setAttribute(String key, Object obj) {
        attributeMap.put(key, obj);
    }


    //框架获取请求转发、重定向路径资源名
    String getViewName() {
        return viewName;
    }

    //框架取值
    Object getAttribute(String key) {
        return attributeMap.get(key);
    }

    //框架获取集合
    HashMap<String, Object> getAttributeMap() {
        return attributeMap;
    }

}
