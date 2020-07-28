package dao;

import domain.Atm;
import orm.annotation.Delete;
import orm.annotation.Insert;
import orm.annotation.Select;
import orm.annotation.Update;

public interface AtmDao {

    @Insert("INSERT INTO ATM VALUES(#{aname},#{apassword},#{abalance});")
    public int insert(Atm atm);

    @Delete("delete from atm where aname = #{aname};")
    public int delete(String name);

    @Update("update atm set apassword=#{apassword},abalance=#{abalance} where aname=#{aname};")
    public int update(Atm atm);

    @Select("select * from atm where aname = #{aname}")
    public Atm selectOne(String aname);

}
