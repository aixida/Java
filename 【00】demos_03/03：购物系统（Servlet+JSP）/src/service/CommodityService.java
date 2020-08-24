package service;

import dao.CommodityDao;
import domain.Commodity;
import util.MySpring;

import java.util.ArrayList;

public class CommodityService {

    private CommodityDao dao = MySpring.getBean("dao.CommodityDao");

    //根据种类编号kid 查询对应的所有具体商品
    public ArrayList<Commodity> selectCommodityById(int kid){
        return dao.selectCommodityByKid(kid);
    }

    //根据商品的cid 查询对应的一条商品记录
    public Commodity selectOne(int cid){
        return dao.selectOne(cid);
    }

    //修改一条记录 商品库存的改变
    public int updateCommodityCount(int cid, int ccount) {
        Commodity commodity = dao.selectOne(cid);
        commodity.setCcount(ccount);
        return dao.update(commodity);
    }

}
