package test;

import service.AreaService;
import service.CityService;

import java.util.ArrayList;

public class Six {
    public static void main(String[] args) {
        CityService service = new CityService();
        ArrayList<String> list = service.select6("美国");
        for (String value:list){
            System.out.println(value);
        }
    }
}
