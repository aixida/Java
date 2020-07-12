package view;

import util.BaseFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("all")
public class RegistFrame extends BaseFrame {

    private RegistFrame(){
        super("注册窗口");
        this.init();
    }
    private static RegistFrame registFrame;
    public synchronized static RegistFrame getRegistFrame(){
        if(registFrame==null){
            registFrame = new RegistFrame();
        }
        return registFrame;
    }

    //添加一些组件的属性
    private JPanel mainPanel = new JPanel();
    private JLabel logoLabel = new JLabel();//logo
    private JLabel titleLabel = new JLabel("请 您 填 写 信 息");//title
    private JLabel accountLabel = new JLabel("请输入账号：");
    private JTextField accountField = new JTextField();//输入账号
    private JLabel passwordLabel = new JLabel("请输入密码：");
    private JTextField passwordField = new JTextField();
    private JLabel balanceLabel = new JLabel("请输入金额：");
    private JTextField balanceField = new JTextField();
    private JButton registButton = new JButton("注 册");
    private JButton resetButton = new JButton("重 置");
    private JButton backButton = new JButton("返 回");

    protected void setFontAndSoOn() {
        mainPanel.setLayout(null);
        logoLabel.setBounds(135,40,40,40);
        logoLabel.setIcon(this.drawImage("img//MySQL.png",40,40));
        titleLabel.setBounds(185,40,200,40);
        titleLabel.setFont(new Font("微软雅黑", Font.BOLD,24));
        accountLabel.setBounds(40,100,140,40);
        accountLabel.setFont(new Font("微软雅黑",Font.BOLD,18));
        accountField.setBounds(170,105,260,32);
        accountField.setFont(new Font("微软雅黑",Font.BOLD,20));
        passwordLabel.setBounds(40,150,140,40);
        passwordLabel.setFont(new Font("微软雅黑",Font.BOLD,18));
        passwordField.setBounds(170,155,260,32);
        passwordField.setFont(new Font("微软雅黑",Font.BOLD,20));
        balanceLabel.setBounds(40,200,140,40);
        balanceLabel.setFont(new Font("微软雅黑",Font.BOLD,18));
        balanceField.setBounds(170,205,260,32);
        balanceField.setFont(new Font("微软雅黑",Font.BOLD,20));
        registButton.setBounds(60,260,100,32);
        registButton.setFont(new Font("微软雅黑",Font.BOLD,14));
        registButton.setBackground(Color.LIGHT_GRAY);
        resetButton.setBounds(190,260,100,32);
        resetButton.setFont(new Font("微软雅黑",Font.BOLD,14));
        resetButton.setBackground(Color.LIGHT_GRAY);
        backButton.setBounds(320,260,100,32);
        backButton.setFont(new Font("微软雅黑",Font.BOLD,14));
        backButton.setBackground(Color.LIGHT_GRAY);
    }
    protected void addElements() {
        mainPanel.add(logoLabel);
        mainPanel.add(titleLabel);
        mainPanel.add(accountLabel);
        mainPanel.add(accountField);
        mainPanel.add(passwordLabel);
        mainPanel.add(passwordField);
        mainPanel.add(balanceLabel);
        mainPanel.add(balanceField);
        mainPanel.add(registButton);
        mainPanel.add(resetButton);
        mainPanel.add(backButton);
        this.add(mainPanel);
    }
    protected void addListener() {
        registButton.addActionListener(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                //获取 名字 密码 余额
                //调用新增方法
                JOptionPane.showMessageDialog(RegistFrame.this,"注册成功,请登录系统");
//                RegistFrame.this.setVisible(false);
//                AtmFrame.getAtmFrame();
            }
        });
        resetButton.addActionListener(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                accountField.setText("");
                passwordField.setText("");
                balanceField.setText("");
            }
        });
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                accountField.setText("");
                passwordField.setText("");
                balanceField.setText("");
                RegistFrame.this.setVisible(false);//当前注册窗口隐藏
                LoginFrame.getLoginFrame().setVisible(true);
            }
        });
    }
    protected void setFrameSelf() {
        this.setBounds(430,200,500,360);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
    }
}
