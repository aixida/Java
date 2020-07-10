package test;

import service.AreaService;

import java.util.ArrayList;
import java.util.HashMap;

public class Five {
    public static void main(String[] args) {
        AreaService service = new AreaService();
        ArrayList<HashMap<String,String>> list = service.select5();
        for(HashMap<String,String> map:list){
            System.out.println(map.get("aname")  + "----" + map.get("sum"));
        }
    }
}
