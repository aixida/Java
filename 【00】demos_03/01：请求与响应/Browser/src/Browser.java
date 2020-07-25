import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

@SuppressWarnings("all")
public class Browser {

    private Scanner input  = new Scanner(System.in);
    private Socket socket;
    private String ip;
    private int port;

    //打开浏览器窗口
    public void openBrowser(){
        //输入url
        //  ip:port/资源名?key=value&key=value
        System.out.print("URL:");
        String url = input.nextLine();
        this.parseUrl(url);
    }

    //解析url
    private void parseUrl(String url){
        int colonIndex = url.indexOf(":");
        int slashIndex = url.indexOf("/");
        ip = url.substring(0,colonIndex);
        port = Integer.parseInt(url.substring(colonIndex+1,slashIndex));
        String contentAndParams = url.substring(slashIndex+1);
        this.request(contentAndParams);
    }

    //创建socket，并把contentAndParams发送出去
    private void request(String contentAndParams){
        try {
            socket = new Socket(ip,port);
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            out.println(contentAndParams);
            out.flush();
            //浏览器等待响应信息
            this.receiveResponseContent();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //接收服务器回写的响应消息
    private void receiveResponseContent(){
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String responseContent = reader.readLine();
            //解析响应信息，并展示出来
            this.parseResponseContentAndShow(responseContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //解析响应信息，并展示出来
    private void parseResponseContentAndShow(String responseContent) {
        //创建两个新的变量，用于存储新一次的请求和参数
        String content = null;//空即没有新的请求
        HashMap<String, String> paramsMap = null;

        //解析<br>标签
        responseContent = responseContent.replace("<br>", "\r\n");
        //解析其它标签
        while (true) {
            int ltIndex = responseContent.indexOf("<");
            int gtIndex = responseContent.indexOf(">");
            if (ltIndex != -1 && gtIndex != -1 && ltIndex < gtIndex) {
                System.out.println(responseContent.substring(0, ltIndex));
                //分析标签是什么类型，做相应的处理
                String tag = responseContent.substring(ltIndex, gtIndex + 1);
                if (tag.contains("input")) {
                    String value = input.nextLine();
                    if (paramsMap == null) {
                        paramsMap = new HashMap<>();//延迟加载
                    }
                    //<input name="name" value="">
                    //<input name="password" value="">
                    String[] keyAndValues = tag.split(" ");
                    for (String keyAndValue : keyAndValues) {
                        if (keyAndValue.contains("=")) {
                            String[] KV = keyAndValue.split("=");
                            if ("name".equals(KV[0])) {
                                paramsMap.put(KV[1].substring(1, KV[1].length() - 1), value);
                            }
                        }
                    }
                } else if(tag.contains("form")){//只能处理一个form表单，多个form则content会被最后一个form的content覆盖
                    //<form action="login" method="">
                    String[] keyAndValues = tag.split(" ");
                    for (String keyAndValue : keyAndValues) {
                        if (keyAndValue.contains("=")) {
                            String[] KV = keyAndValue.split("=");
                            if ("action".equals(KV[0])) {
                                //产生一个新的请求
                                content = KV[1].substring(1, KV[1].length() - 1);
                            }
                        }
                    }
                }
                responseContent = responseContent.substring(gtIndex+1);
            } else {
                System.out.println(responseContent);//则直接输出全部内容
                break;
            }
        }
        //-----------自此，将所有的响应信息解析完毕------------------------------------------------
        //如果标签中遇到了form，表示我还有一次新的请求
        this.sendNewRequest(content,paramsMap);
    }

    //遇到form表单，发送新的请求
    private void sendNewRequest(String content,HashMap<String,String> paramsMap){
        if(content != null){
            StringBuilder contentAndParams = new StringBuilder(content);
            if(paramsMap != null){
                contentAndParams.append("?");
                Iterator<String> it = paramsMap.keySet().iterator();
                while (it.hasNext()){
                    String key = it.next();
                    String value = paramsMap.get(key);
                    contentAndParams.append(key);
                    contentAndParams.append("=");
                    contentAndParams.append(value);
                    contentAndParams.append("&");
                }
                //循环执行完毕后，最终多了一个&符号，将其删除
                contentAndParams.delete(contentAndParams.length()-1,contentAndParams.length());
            }
            this.request(contentAndParams.toString());
        }
    }

}
