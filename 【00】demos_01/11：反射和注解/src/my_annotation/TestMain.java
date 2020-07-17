package my_annotation;

public class TestMain {
    public static void main(String[] args) {
        Person p = MySpring.getBean("my_annotation.Person");
        System.out.println(p.getName() + "--" + p.getAge() + "--" + p.getSex());
    }
}
