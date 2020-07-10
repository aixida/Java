package service;

import dao.CountryDao;

import java.util.ArrayList;
import java.util.HashMap;

public class CountryService {

    CountryDao dao = new CountryDao();

    public ArrayList<HashMap<String,String>> select2(){
        return dao.select2();
    }

    public ArrayList<String> select4(String cityName){
        return dao.select4(cityName);
    }

    public String select7(){
        return dao.select7();
    }

    public ArrayList<HashMap<String,String>> select8(){
        return dao.select8();
    }

    public ArrayList<String> select9(int size){
        return dao.select9(size);
    }

    public ArrayList<String> select11(int size){
        return dao.select11(size);
    }
}
