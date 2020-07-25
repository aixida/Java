package server;

import java.util.HashMap;

public class HttpServletRequest {

    private String contnet;
    private HashMap<String,String> paramsMap;

    public HttpServletRequest(){}
    public HttpServletRequest(String contnet, HashMap<String, String> paramsMap) {
        this.contnet = contnet;
        this.paramsMap = paramsMap;
    }

    public String getParameter(String key){
        return this.paramsMap.get(key);
    }

    public String getContnet() {
        return contnet;
    }

    public void setContnet(String contnet) {
        this.contnet = contnet;
    }

    public void setParamsMap(HashMap<String, String> paramsMap) {
        this.paramsMap = paramsMap;
    }
}