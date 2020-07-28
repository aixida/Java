package ormdao;

import domain.Student;
import orm.annotation.Delete;
import orm.annotation.Insert;
import orm.annotation.Select;
import orm.annotation.Update;

import java.util.List;
import java.util.Map;

//方案二[模拟MyBatis]
//使用注解与动态代理模式, 使dao层变成接口
public interface StudentDao {

    @Insert("insert into student values(#{id},#{name},#{sex},#{birth},#{ctime});")
    public int insert(Student student);

    @Insert("insert into student values(#{id},#{name},#{sex},#{birth},#{ctime});")
    public int insert(Map map);

    @Delete("delete from student where id = #{id};")
    public int delete(int id);

    @Update("update student set name=#{name},sex=#{sex},birth=#{birth},ctime=#{ctime} where id=#{id};")
    public int update(Student student);

    @Select("select * from student where id = #{id}")
    public Student selectOne(int id);

    @Select("select * from student")
    public List<Student> selectList();


}
