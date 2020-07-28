package dao;

import domain.Student;
import orm.RowMapper;
import orm.SqlSessionFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class StudentDao {

    private StudentDao(){};
    private static StudentDao dao;
    public static StudentDao getInstance(){
        if (dao == null){
            dao = new StudentDao();
        }
        return dao;
    }

    private SqlSessionFactory sqlSession = SqlSessionFactory.getInstance();

    //============================ 方案二[模拟MyBatis] - begin ============================

    //增加一条记录
    public int insert(Student student) {
        String sql = "insert into student values(#{id},#{name},#{sex},#{birth},#{ctime});";
        return sqlSession.insert(sql,student);
    }

    public int insert(Map map) {
        String sql = "insert into student values(#{id},#{name},#{sex},#{birth},#{ctime});";
        return sqlSession.insert(sql,map);
    }

    //删除一条记录
    public int delete(int id) {
        String sql = "delete from student where id = #{id};";
        return sqlSession.delete(sql,id);
    }

    //删库跑路咯
    public int delete() {
        String sql = "delete from student";
        return sqlSession.delete(sql);
    }

    //修改一条记录
    public int update(Student student) {
        String sql = "update student set name=#{name},sex=#{sex},birth=#{birth},ctime=#{ctime} where id=#{id};";
        return sqlSession.update(sql,student);
    }

    public int update(Map map) {
        String sql = "update student set name=#{name},sex=#{sex},birth=#{birth},ctime=#{ctime} where id=#{id};";
        return sqlSession.update(sql,map);
    }

    //单条查询
    public Map<String ,Object> selectOne(int id){
        String sql = "select * from student where id = #{id}";
        return sqlSession.selectOne(sql,id,Map.class);
    }

    //============================ 方案一[模拟MyBatis] - end ============================

    //以下为方案一[模拟SpringJDBC]

    public int insert1(Student student) {
        String sql = "insert into student values(?,?,?,?,?);";
        return sqlSession.insert1(sql,student.getId(),student.getName(),student.getSex(),student.getBirth(),student.getCtime());
    }

    public int insert1(Map map) {
        String sql = "insert into student values(?,?,?,?,?);";
        return sqlSession.insert1(sql,map.get("id"),map.get("name"),map.get("sex"),map.get("birth"),map.get("ctime"));
    }

    //删除一条记录
    public int delete1(int id) {
        String sql = "delete from student where id = ?;";
        return sqlSession.delete1(sql,id);
    }

    //删库跑路咯
    public int delete1() {
        String sql = "delete from student";
        return sqlSession.delete1(sql);
    }

    //修改一条记录
    public int update1(Student student) {
        String sql = "update student set name=?,sex=?,birth=?,ctime=? where id=?;";
        return sqlSession.update1(sql,student.getName(),student.getSex(),student.getBirth(),student.getCtime(),student.getId());
    }

    public int update1(Map map) {
        String sql = "update student set name=?,sex=?,birth=?,ctime=? where id=?;";
        return sqlSession.update1(sql,map.get("name"),map.get("sex"),map.get("birth"),map.get("ctime"),map.get("id"));
    }

    //单条查询
    public Student selectOne1(int id){
        String sql = "select * from student where id = ?";
        return sqlSession.selectOne1(sql, new RowMapper() {//策略模式
            @Override
            public Object mapperRow(ResultSet rs) {
                Student student = new Student();
                try {
                    student.setId(rs.getInt("id"));
                    student.setName(rs.getString("name"));
                    student.setSex(rs.getString("sex"));
                    student.setBirth(rs.getInt("birth"));
                    student.setCtime(rs.getDate("ctime"));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                return student;
            }
        }, id);
    }

    //多条查询
    public List<Student> selectList1(){
        String sql = "select * from student";
        return sqlSession.selectList1(sql, new RowMapper() {//策略模式
            @Override
            public Object mapperRow(ResultSet rs) {
                Student student = new Student();
                try {
                    student.setId(rs.getInt("id"));
                    student.setName(rs.getString("name"));
                    student.setSex(rs.getString("sex"));
                    student.setBirth(rs.getInt("birth"));
                    student.setCtime(rs.getDate("ctime"));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                return student;
            }
        });
    }

}
