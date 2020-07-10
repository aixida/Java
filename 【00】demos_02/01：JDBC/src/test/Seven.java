package test;

import service.CountryService;

public class Seven {
    public static void main(String[] args) {
        CountryService service = new CountryService();
        String value = service.select7();
        System.out.println(value);
    }
}
