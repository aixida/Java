import java.util.ArrayList;

//仓库
public class WareHouse {

    private WareHouse(){}
    private static WareHouse warehouse = new WareHouse();
    public static WareHouse getInstance(){
        return warehouse;
    }

    private ArrayList<String> list = new ArrayList<>();

    //向集合中添加元素
    public void add(){
        if(list.size() < 20){
            list.add("X");
        }else{
            return;
        }
    }

    //向集合中取元素
    public void get(){
        if(list.size() > 0){
            list.remove(0);
        }else{
            return;
        }
    }

}

