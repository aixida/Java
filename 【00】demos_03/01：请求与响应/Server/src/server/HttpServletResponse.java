package server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

//自己创建的一个对象
//存储响应信息
public class HttpServletResponse {

    private StringBuilder responseContent = new StringBuilder();

    //直接添加相应信息
    public void write(String str){
        this.responseContent.append(str);
    }

    //读取文件（文件内容是响应信息）
    public void sendRedirect(String path){
        try {
            File file = new File("src//view//" + path);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String value = reader.readLine();
            while(value != null){
                this.responseContent.append(value);
                value = reader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getResponseContent(){
        return this.responseContent.toString();
    }

}