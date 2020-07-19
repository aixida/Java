package view;

import util.BaseFrame;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("all")
public class ExamFrame extends BaseFrame {

    private ExamFrame(){
        super("考试窗口");
        this.init();
    }
    private static ExamFrame examFrame;
    public synchronized static ExamFrame getExamFrame(){
        if (examFrame == null){
            examFrame = new ExamFrame();
        }
        return examFrame;
    }

    //添加三个panel 区域的分割
    private JPanel mainPanel = new JPanel();//负责答题主页面展示
    private JPanel messagePanel = new JPanel();//负责右侧信息展示
    private JPanel buttonPanel = new JPanel();//负责下方按钮的展示
    //添加主要答题的组件
    private JTextArea examArea = new JTextArea();//考试文本域 展示题目
    private JScrollPane scrollPane = new JScrollPane(examArea);//滚动条
    //添加右侧信息的组件
    private JLabel pictureLabel = new JLabel();//展示图片信息
    private JLabel nowNumLabel = new JLabel("当前题号：");//提示当前的题号
    private JLabel totalCountLabel = new JLabel("题目总数：");//提示题目的总数
    private JLabel answerCountLabel = new JLabel("已答题数：");//提示已经答过的题目数量
    private JLabel unanswerCountLabel = new JLabel("未答题数：");//提示未答题数量
    private JTextField nowNumField = new JTextField("0");//展示题号
    private JTextField totalCountField = new JTextField("0");//展示总数
    private JTextField answerCountField = new JTextField("0");//展示已答数
    private JTextField unanswerCountField = new JTextField("0");//展示未答数
    private JLabel timeLabel = new JLabel("剩余答题时间");//提示剩余时间
    private JLabel realTimeLabel = new JLabel("00:00:00");//倒计时真实时间
    //添加下方按钮的组件
    private JButton aButton = new JButton("A");//a按钮
    private JButton bButton = new JButton("B");//b按钮
    private JButton cButton = new JButton("C");//c按钮
    private JButton dButton = new JButton("D");//d按钮
    private JButton prevButton = new JButton("上一题");//previous题
    private JButton nextButton = new JButton("下一题");//next题
    private JButton submitButton = new JButton("提交试卷");//提交按钮

    @Override
    protected void setFontAndSoOn() {
        //设置panel布局管理---->自定义
        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.LIGHT_GRAY);

        //设置message区域的位置
        messagePanel.setLayout(null);
        messagePanel.setBounds(680,10,300,550);
        messagePanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        //设置button区域的位置
        buttonPanel.setLayout(null);
        buttonPanel.setBounds(16,470,650,90);
        buttonPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        //手动设置每一个组件的位置 字体 背景
        scrollPane.setBounds(16,10,650,450);

        examArea.setFont(new Font("黑体",Font.BOLD,34));
        examArea.setEnabled(false);//文本域中的文字不能编辑

        pictureLabel.setBounds(10,10,280,230);
        pictureLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        //pictureLabel.setIcon(null);//展示图片信息

        nowNumLabel.setBounds(40,270,100,30);
        nowNumLabel.setFont(new Font("黑体",Font.PLAIN,20));
        nowNumField.setBounds(150,270,100,30);
        nowNumField.setFont(new Font("黑体",Font.BOLD,20));
        nowNumField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        nowNumField.setEnabled(false);
        nowNumField.setHorizontalAlignment(JTextField.CENTER);

        totalCountLabel.setBounds(40,310,100,30);
        totalCountLabel.setFont(new Font("黑体",Font.PLAIN,20));
        totalCountField.setBounds(150,310,100,30);
        totalCountField.setFont(new Font("黑体",Font.BOLD,20));
        totalCountField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        totalCountField.setEnabled(false);
        totalCountField.setHorizontalAlignment(JTextField.CENTER);

        answerCountLabel.setBounds(40,350,100,30);
        answerCountLabel.setFont(new Font("黑体",Font.PLAIN,20));
        answerCountField.setBounds(150,350,100,30);
        answerCountField.setFont(new Font("黑体",Font.BOLD,20));
        answerCountField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        answerCountField.setEnabled(false);
        answerCountField.setHorizontalAlignment(JTextField.CENTER);

        unanswerCountLabel.setBounds(40,390,100,30);
        unanswerCountLabel.setFont(new Font("黑体",Font.PLAIN,20));
        unanswerCountField.setBounds(150,390,100,30);
        unanswerCountField.setFont(new Font("黑体",Font.BOLD,20));
        unanswerCountField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        unanswerCountField.setEnabled(false);
        unanswerCountField.setHorizontalAlignment(JTextField.CENTER);

        timeLabel.setBounds(90,460,150,30);
        timeLabel.setFont(new Font("黑体",Font.PLAIN,20));
        timeLabel.setForeground(Color.BLUE);
        realTimeLabel.setBounds(108,490,150,30);
        realTimeLabel.setFont(new Font("黑体",Font.BOLD,20));
        realTimeLabel.setForeground(Color.BLUE);

        aButton.setBounds(40,10,120,30);
        bButton.setBounds(190,10,120,30);
        cButton.setBounds(340,10,120,30);
        dButton.setBounds(490,10,120,30);
        prevButton.setBounds(40,50,100,30);
        nextButton.setBounds(510,50,100,30);
        submitButton.setBounds(276,50,100,30);
        submitButton.setForeground(Color.RED);
        submitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    @Override
    protected void addElement() {
        messagePanel.add(pictureLabel);
        messagePanel.add(nowNumLabel);
        messagePanel.add(nowNumField);
        messagePanel.add(totalCountLabel);
        messagePanel.add(totalCountField);
        messagePanel.add(answerCountLabel);
        messagePanel.add(answerCountField);
        messagePanel.add(unanswerCountLabel);
        messagePanel.add(unanswerCountField);
        messagePanel.add(timeLabel);
        messagePanel.add(realTimeLabel);
        buttonPanel.add(aButton);
        buttonPanel.add(bButton);
        buttonPanel.add(cButton);
        buttonPanel.add(dButton);
        buttonPanel.add(prevButton);
        buttonPanel.add(nextButton);
        buttonPanel.add(submitButton);
        mainPanel.add(scrollPane);
        mainPanel.add(messagePanel);
        mainPanel.add(buttonPanel);
        this.add(mainPanel);
    }

    @Override
    protected void addListener() {

    }

    @Override
    protected void setFrameSelf() {
        this.setBounds(260,130,1000,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);//不想让窗体拖拽大小
        this.setVisible(true);//最终展示整个窗体
    }

}
