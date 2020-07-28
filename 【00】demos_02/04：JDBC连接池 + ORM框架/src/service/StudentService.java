package service;

import domain.Student;
import orm.SqlSessionFactory;
import ormdao.StudentDao;

import java.util.List;
import java.util.Map;

public class StudentService {

    private StudentService(){};
    private static StudentService servide;
    public static StudentService getInstance(){
        if (servide == null){
            servide = new StudentService();
        }
        return servide;
    }

    private StudentDao dao = SqlSessionFactory.getInstance().getMapper(StudentDao.class);//代理对象

    public int update(Student student) {
        return dao.update(student);
    }
//
//    public int update(Map map) {
//        return dao.update(map);
//    }
//
    public int delete(int id) {
        return dao.delete(id);
    }

    public long insert(Student student) {
        return dao.insert(student);
    }

    public long insert(Map map) {
        return dao.insert(map);
    }

    public Student selectOne(int id){
        return dao.selectOne(id);
    }

    public List<Student> selectList(){
        return dao.selectList();
    }

}
