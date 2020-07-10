package service;

import dao.AreaDao;
import domain.Area;

import java.util.ArrayList;
import java.util.HashMap;

public class AreaService {

    AreaDao dao = new AreaDao();

    public ArrayList<String> select1(int begin, int end){
        return dao.select1(begin, end);
    }

    public ArrayList<HashMap<String,String>> select3(){
        return dao.select3();
    }

    public ArrayList<HashMap<String,String>> select5(){
        return dao.select5();
    }

    public ArrayList<HashMap<String,String>> select10(){
        return dao.select10();
    }
}
