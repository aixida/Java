//生产者
public class Producer extends Thread {

    //一直向仓库中添加元素
    @Override
    public void run() {
        while(true){
            WareHouse.getInstance().add();
            System.out.println("Producer存入了一件货物");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}