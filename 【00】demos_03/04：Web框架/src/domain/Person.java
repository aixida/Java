package domain;

public class Person {

    private String name;
    private Integer pass;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", pass=" + pass +
                '}';
    }

    public Person() {
    }

    public Person(String name, Integer pass) {
        this.name = name;
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPass() {
        return pass;
    }

    public void setPass(Integer pass) {
        this.pass = pass;
    }
}
