package test;

import service.AreaService;

import java.util.ArrayList;
import java.util.HashMap;

public class Three {
    public static void main(String[] args) {
        AreaService service = new AreaService();
        ArrayList<HashMap<String,String>> list = service.select3();
        for(HashMap<String,String> map:list){
            System.out.println(map.get("aname")  + "----" + map.get("avg"));
        }
    }
}
