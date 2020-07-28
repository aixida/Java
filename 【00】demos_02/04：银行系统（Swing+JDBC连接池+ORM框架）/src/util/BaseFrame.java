package util;

import javax.swing.*;
import java.awt.*;

public abstract class BaseFrame extends JFrame {
    //模板模式

    public BaseFrame(){}
    public BaseFrame(String title){
        super(title);
    }

    //设计一个具体方法 规定加载窗体时的执行顺序
    protected void init(){
        this.setFontAndSoOn();
        this.addElements();
        this.addListener();
        this.setFrameSelf();
    }

    //设计一个通用的画图方法 给我图片的路径及宽高
    protected ImageIcon drawImage(String path,int width,int height){
        //通过给定的路径创建一个icon对象
        ImageIcon imageIcon = new ImageIcon(path);
        //设置icon对象内的image对象信息
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(width,height, Image.SCALE_DEFAULT));
        //将icon对象返回
        return imageIcon;
    }

    //1.用来设置 字体 颜色 背景 布局管理 等等
    protected abstract void setFontAndSoOn();
    //2.用来添加所有的组件
    protected abstract void addElements();
    //3.添加事件监听器
    protected abstract void addListener();
    //4.设置窗体自身的属性
    protected abstract void setFrameSelf();

}
