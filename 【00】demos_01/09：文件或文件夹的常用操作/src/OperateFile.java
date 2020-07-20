import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class OperateFile {

    //1.父文件夹的遍历
    public void showFatherFiles(String path){//文件或文件夹路径
        File file = new File(path);
        File pfile = file.getParentFile();
        while(pfile != null){
            System.out.println(pfile.getAbsolutePath());//输出父文件夹的绝对路径名
            pfile = pfile.getParentFile();
        }
    }

    //2.文件夹遍历
    public void showFiles(File file){//文件夹对象
        File[] files = file.listFiles();
        if(files != null && files.length != 0){//判断file是文件夹 且 文件夹内有元素
            for(File f:files){
                this.showFiles(f);
            }
        }
        //file是文件或者是空文件夹
        System.out.println(file.getAbsolutePath());
    }

    //3.文件夹遍历删除/文件删除
    public void deleteFiles(File file){//文件夹/文件对象
        File[] files = file.listFiles();
        if(files != null && files.length != 0){
            for(File f:files){
                this.deleteFiles(f);
            }
        }
        file.delete();
    }

    //4.文件的加密
    //	- file：被加密的文件对象
    //	- path：文件加密后写入到到目标路径下
    public void encryptFiles(File file, String path){
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(file);
            File newFile = new File(path + "//" + file.getName());//无则自动创建该文件
            fos = new FileOutputStream(newFile);

            byte[] b = new byte[1024];//通常创建的数组  1kb--8kb之间
            int count = fis.read(b);
            while(count != -1) {
                //很简单的加密方法：每一次数组的前两个元素位置互换 1024
                byte temp = b[0];
                b[0] = b[1];
                b[1] = temp;
                fos.write(b,0,count);//将读取到的有效字节 写入
                fos.flush();
                count = fis.read(b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(fis!=null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(fos!=null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //5.文件（或者文件夹）的复制
    //	- file：被复制的文件/文件夹对象
    //	- path：文件/文件夹复制目标路径
    public void superCopyFile(File file, String newPath){
        //获取file的绝对路径, 拼串的方式获取新文件或者新文件夹的路径
        String oldFilePath = file.getAbsolutePath();
        int idx1 = oldFilePath.lastIndexOf('\\');
        int idx2 = oldFilePath.lastIndexOf('/');
        int idx = idx1 > idx2 ? idx1 :idx2;
        String newFilePath = newPath + oldFilePath.substring(idx, oldFilePath.length());

        File newFile = new File(newFilePath);

        //判断当前传递进来的file是个文件还是文件夹  isFile isDirectory listFiles
        File[] files = file.listFiles();
        if(files != null){//文件夹
            newFile.mkdir();
            System.out.println(newFile.getName() + " 文件夹复制完毕");

            if(files.length != 0){//非空文件夹
                for(File f:files){
                    this.superCopyFile(f,newFilePath);
                }
            }

        }else{//文件
            FileInputStream fis = null;
            FileOutputStream fos = null;
            try {
                fis = new FileInputStream(file);
                fos = new FileOutputStream(newFile);
                byte[] b = new byte[1024];
                int count = fis.read(b);
                while(count!=-1){
                    fos.write(b,0,count);
                    fos.flush();
                    count = fis.read(b);
                }
                System.out.println(newFile.getName() + " 文件复制完毕");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if(fis!=null) {
                        fis.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    if(fos!=null) {
                        fos.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //6.文件（或者文件夹）的剪切
    //	- file：被复制的文件/文件夹对象
    //	- path：文件/文件夹复制目标路径
    public void shear(File file, String newPath){
        this.superCopyFile(file, newPath);
        this.deleteFiles(file);
    }

    public static void main(String[] args) {
        OperateFile of = new OperateFile();
        //...
    }

}
