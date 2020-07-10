package test;

import service.CountryService;

import java.util.ArrayList;

public class Eleven {
    public static void main(String[] args) {
        CountryService service = new CountryService();
        ArrayList<String> list = service.select11(5000);
        for (String value:list){
            System.out.println(value);
        }
    }
}
