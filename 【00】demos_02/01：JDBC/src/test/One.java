package test;

import service.AreaService;

import java.util.ArrayList;

public class One {
    public static void main(String[] args) {
        AreaService service = new AreaService();
        ArrayList<String> list = service.select1(1000, 2000);
        for (String value:list){
            System.out.println(value);
        }
    }
}
