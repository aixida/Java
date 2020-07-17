package my_annotation;

public class Person {

    private String name;
    private Integer age;
    private String sex;

    @MyAnnotation({"zgh","18","男"})
    public Person(){}

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }

}
