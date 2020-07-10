package test;

import service.CountryService;

import java.util.ArrayList;

public class Four {
    public static void main(String[] args) {
        CountryService service = new CountryService();
        ArrayList<String> list = service.select4("哈尔滨");
        for (String value:list){
            System.out.println(value);
        }
    }
}
