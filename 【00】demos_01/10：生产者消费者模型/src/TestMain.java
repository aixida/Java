public class TestMain {
    public static void main(String[] args) {
        Producer producer = new Producer();//一个生产者
        Consumer customer1 = new Consumer();//两个消费者
        Consumer customer2 = new Consumer();
        producer.start();
        customer1.start();
        customer2.start();
    }
}
