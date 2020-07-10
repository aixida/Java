package test;

import service.CityService;

import java.util.ArrayList;

public class Twelve {
    public static void main(String[] args) {
        CityService service = new CityService();
        ArrayList<String> list = service.select12("杭州");
        for(String value:list){
            System.out.println(value);
        }
    }
}
