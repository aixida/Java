package util;

import domain.User;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;

//文件加载 + 提交
public class FileLoaderAndCommit {

    private File file = null;
    public FileLoaderAndCommit(String fileName){
        this.file = new File(fileName);
    }

    //读取文件，存入缓存
    public HashMap<String,User> loadFile(){
        HashMap<String,User> userBox = new HashMap<>();
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            String value = br.readLine();
            while(value != null){
                String[] userValue = value.split("-");
                User user = new User(userValue[0],userValue[1],Float.parseFloat(userValue[2]));
                userBox.put(user.getAname(),user);
                value = br.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                if(fr != null) {
                    fr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return userBox;
    }

    //缓存中的内容，覆盖式写入文件
    public void commit(HashMap<String,User> userBox){
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter(file);
            bw = new BufferedWriter(fw);
            Iterator<String> names = userBox.keySet().iterator();
            while(names.hasNext()){
                String name = names.next();
                User user = userBox.get(name);
                StringBuilder builder = new StringBuilder(user.getAname());
                builder.append("-");
                builder.append(user.getApassword());
                builder.append("-");
                builder.append(user.getAbalance());

                bw.write(builder.toString());
                bw.newLine();
                bw.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try {
                if(fw != null) {
                    fw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
