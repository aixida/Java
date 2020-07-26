import java.util.Vector;

//12306售票系统
public class System12306 {

    //单例模式（饿汉式）
    private System12306(){}
    private static System12306 sys = new System12306();
    public static System12306 getInstance(){
        return sys;
    }

    //Vector或者Stack 线程安全
    private Vector<Ticket> tickets = new Vector<>();

    //当前系统创建后给tickets集合赋值
    {
        for(int i = 10; i < 100; i++){
            tickets.add(new Ticket("北京"+i,"深圳"+i,(i%5+5)*25F));//价格随机
        }
    }

    //获取一张火车票
    public Ticket getTicket(){
        try {
            return tickets.remove(0);
        }catch (Exception e){
            return null;//无票
        }
    }

}
