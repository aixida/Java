public class TestMain {

    public static void main(String[] args){
        //四根筷子
        Chopstick c1 = new Chopstick(1);
        Chopstick c2 = new Chopstick(2);
        Chopstick c3 = new Chopstick(3);
        Chopstick c4 = new Chopstick(4);

        //四位哲学家
        Philosopher p1 = new Philosopher("哲学家a",c1,c2,0);
        Philosopher p2 = new Philosopher("哲学家b",c2,c3,0);
        Philosopher p3 = new Philosopher("哲学家c",c3,c4,0);
        Philosopher p4 = new Philosopher("哲学家d",c4,c1,0);

        p1.start();
        p2.start();
        p3.start();
        p4.start();
    }

}
