//消费者
public class Consumer extends Thread {

    //一直从仓库里面拿东西
    @Override
    public void run() {
        while(true){
            WareHouse.getInstance().get();
            System.out.println("Customer拿走了一件东东");
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}