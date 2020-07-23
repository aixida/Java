public class TestMain {
    public static void main(String[] args) {
        Producer producer = new Producer();//一个生产者
        //设置线程的优先级，让生产者先干活
        //参数：优先级别1-10
        //	数字越高，优先级越高，更加容易获得cpu分配的时间碎片
        producer.setPriority(10);

        Consumer customer1 = new Consumer();//两个消费者
        Consumer customer2 = new Consumer();
        producer.start();
        customer1.start();
        customer2.start();
    }
}
