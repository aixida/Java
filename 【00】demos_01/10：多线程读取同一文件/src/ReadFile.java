import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ReadFile extends Thread{
    private long step;
    private long num;

    public ReadFile(long step,long num){
        this.step = step;
        this.num = num;
    }

    @Override
    public void run() {
        File f = new File("src\\test.txt");
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(f);
            fis.skip(step);
            while(num != 0){
                System.out.println(this.getName() + "读取：" + (char)fis.read());
                num--;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ReadFile rf1 = new ReadFile(0,10);
        ReadFile rf2 = new ReadFile(10,10);
        ReadFile rf3 = new ReadFile(20,6);
        rf1.start();
        rf2.start();
        rf3.start();
    }
}