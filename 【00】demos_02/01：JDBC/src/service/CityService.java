package service;

import dao.CityDao;

import java.util.ArrayList;

public class CityService {

    CityDao dao = new CityDao();

    public ArrayList<String> select6(String countryName){
        return dao.select6(countryName);
    }

    public ArrayList<String> select12(String cityName){
        return dao.select12(cityName);
    }
}
