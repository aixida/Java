//哲学家
public class Philosopher extends Thread{

    private String pname;//哲学家的名字
    private Chopstick left;//左手的筷子
    private Chopstick right;//右手的筷子
    private long time;

    public Philosopher(String pname,Chopstick left,Chopstick right,long time){
        this.pname = pname;
        this.left = left;
        this.right = right;
        this.time = time;
    }

    public void run(){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (left) {
            System.out.println(this.pname + "拿起了左手边的" + this.left.getNum() + "筷子");
            synchronized (right){
                System.out.println(this.pname + "拿起了右手边的" + this.right.getNum() + "筷子");
                System.out.println(this.pname + "开始狼吞虎咽的吃起来啦");
            }
        }
    }

}
