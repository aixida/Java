package service;

import dao.KindDao;
import domain.Kind;
import util.MySpring;

import java.util.ArrayList;

public class KindService {

    private KindDao dao = MySpring.getBean("dao.KindDao");

    //业务方法 查询所有种类   返回ArrayList<Kind>
    public ArrayList<Kind> selectAllKind(){
        return dao.selectAllKind();
    }
}
