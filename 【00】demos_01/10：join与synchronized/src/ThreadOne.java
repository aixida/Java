public class ThreadOne extends Thread {

    public void run(){
        System.out.println("thread-one start");
        ThreadTwo two = new ThreadTwo();
        two.start();
        try {
            two.join(2000);//线程2加入线程1里面
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("thread-one end");
    }

}
