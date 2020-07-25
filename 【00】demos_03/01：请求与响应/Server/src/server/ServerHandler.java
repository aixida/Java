package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;

public class ServerHandler extends Thread{

    private Socket socket;
    public ServerHandler(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        //1.读取消息
        //2.解析
        //3.找人做事
        //4.响应回去
        this.receiveRequest();
    }

    //读取消息
    private void receiveRequest(){
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String contentAndParams = reader.readLine();
            this.parseContentAndParams(contentAndParams);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //解析
    private void parseContentAndParams(String contentAndParams){
        String content = null;
        HashMap<String,String> paramsMap = null;
        int questionMarkIndex = contentAndParams.indexOf("?");
        if(questionMarkIndex != -1){//携带了参数
            content = contentAndParams.substring(0,questionMarkIndex);
            paramsMap = new HashMap<String,String>();
            String params = contentAndParams.substring(questionMarkIndex+1);
            String[] keyAndValues = params.split("&");
            for(String keyAndValue:keyAndValues){
                String[] KV = keyAndValue.split("=");
                paramsMap.put(KV[0],KV[1]);
            }
        }else{//没有携带参数
            content = contentAndParams;
        }
        //-------------自此，将请求发送过来的字符串解析完毕了----------------------
        //自己创建两个对象
        //  一个是为了 包含所有请求携带的信息（现在有：资源--content，参数--paramsMap）
        //  另一个是为了 接收响应回来的结果，创建时是空对象，在Controller执行完毕后，将其填满
        HttpServletRequest request = new HttpServletRequest(content,paramsMap);
        HttpServletResponse response = new HttpServletResponse();
        ServletController.findController(request,response);
        //-------------上面这个方法执行完毕，真实的Controller里面的那个service方法执行完了--------------------------------
        // response对象内应该就有响应的消息了
        this.responseToBrowser(response);
    }

    //响应回去
    private void responseToBrowser(HttpServletResponse response){
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            out.println(response.getResponseContent());
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}