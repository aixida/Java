package test;

import service.CountryService;

import java.util.ArrayList;

public class Nine {
    public static void main(String[] args) {
        CountryService service = new CountryService();
        ArrayList<String> list = service.select9(1500);
        for (String value:list){
            System.out.println(value);
        }
    }
}
