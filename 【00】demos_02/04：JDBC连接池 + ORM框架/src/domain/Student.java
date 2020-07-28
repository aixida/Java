package domain;

import java.sql.Date;

public class Student {

    private Integer id;
    private String name;
    private String sex;
    private Integer birth;
    private Date ctime;

    public Student() {
    }

    public Student(Integer id, String name, String sex, Integer birth, Date ctime) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.birth = birth;
        this.ctime = ctime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getBirth() {
        return birth;
    }

    public void setBirth(Integer birth) {
        this.birth = birth;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

}
