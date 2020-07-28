package service;

import dao.StudentDao;
import domain.Student;

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

    private StudentDao dao = StudentDao.getInstance();

    public int update(Student student) {
        return dao.update(student);
    }

    public int update(Map map) {
        return dao.update(map);
    }

    public int delete(int id) {
        return dao.delete1(id);
    }

    public long insert(Student student) {
        return dao.insert1(student);
    }

    public long insert(Map map) {
        return dao.insert(map);
    }

    public Map<String, Object> selectOne(int id){
        return dao.selectOne(id);
    }
    public List<Student> selectList(){
        return dao.selectList1();
    }

}
