package view;

import service.UserService;
import util.BaseFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LoginFrame extends BaseFrame {

    private LoginFrame(){
        super("登录窗口");
        this.init();
    }
    private static LoginFrame loginFrame;
    public synchronized static LoginFrame getLoginFrame(){
        if (loginFrame == null){
            loginFrame = new LoginFrame();
        }
        return loginFrame;
    }

    private UserService service = new UserService();

    //创建一个面板
    private JPanel mainPanel = new JPanel();
    //创建title显示标题
    private JLabel titleLabel = new JLabel("假 的 考 试 系 统");
    //创建账号和密码的标题
    private JLabel accountLabel = new JLabel("账 户：");
    private JLabel passwordLabel = new JLabel("密 码：");
    //创建输入账号和密码的文本框/密码框
    private JTextField accountField = new JTextField();
    private JPasswordField passwordField = new JPasswordField();
    //创建两个按钮
    private JButton loginButton = new JButton("登 录");
    private JButton exitButton = new JButton("退 出");

    //设置每一个组件的位置 大小 字体 布局等等
    protected void setFontAndSoOn(){
        //设置组件的位置----布局管理
        // 边界式BorderLayout(JFrame) 流式FlowLayout(JPanel)
        // 网格式GridLayout 自定义(null)
        //设置panel的布局管理为自定义方式
        mainPanel.setLayout(null);
        //mainPanel.setBackground(Color.WHITE);
        //设置标题组件的位置和字体大小
        titleLabel.setBounds(120,40,340,35);
        titleLabel.setFont(new Font("黑体",Font.BOLD,34));
        //设置用户名label位置和字体大小
        accountLabel.setBounds(94,124,90,30);
        accountLabel.setFont(new Font("黑体",Font.BOLD,24));
        //设置用户名filed位置和字体大小
        accountField.setBounds(204,124,260,30);
        accountField.setFont(new Font("黑体",Font.BOLD,24));
        //设置密码label位置和字体大小
        passwordLabel.setBounds(94,174,90,30);
        passwordLabel.setFont(new Font("黑体",Font.BOLD,24));
        //设置密码field位置和字体大小
        passwordField.setBounds(204,174,260,30);
        passwordField.setFont(new Font("黑体",Font.BOLD,24));
        //设置登录按钮的位置和文字大小
        loginButton.setBounds(154,232,100,30);
        loginButton.setFont(new Font("黑体",Font.BOLD,22));
        //设置退出按钮的位置和文字大小
        exitButton.setBounds(304,232,100,30);
        exitButton.setFont(new Font("黑体",Font.BOLD,22));
    }

    //将所有的组件添加在窗体上
    protected void addElement(){
        //将所有的组件添加至窗体上
        mainPanel.add(titleLabel);
        mainPanel.add(accountLabel);
        mainPanel.add(accountField);
        mainPanel.add(passwordLabel);
        mainPanel.add(passwordField);
        mainPanel.add(loginButton);
        mainPanel.add(exitButton);
        this.add(mainPanel);
    }

    //绑定事件监听
    protected void addListener(){

        //绑定事件监听器
        //  构造方法私有的  构造方法公有的但是没有无参数的  抽象类或接口
        ActionListener listener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //1.获取用户输入的账号和密码
                //  从登录窗口上的组件内获取   文本框  密码框
                String account = accountField.getText();
                String password = new String(passwordField.getPassword());
                //2.调用Service层的登录方法
                String result = service.login(account,password);
                //3.判定最终的结果
                if (result.equals("登录成功")){
                    ExamFrame.getExamFrame();
                }else{
                    //弹出一个警告框 告诉登录失败啦
                    JOptionPane.showMessageDialog(LoginFrame.this,result);
                    //设置文本框和密码框的值为空
                    accountField.setText("");
                    passwordField.setText("");
                }
            }
        };
        loginButton.addActionListener(listener);//观察者模式



    }

    protected void setFrameSelf(){
        //设置窗体起始位置和大小
        this.setBounds(600,280,560,340);
        //设置点击关闭退出程序
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置窗体大小不可拖拽
        this.setResizable(false);
        //设置窗体显示状态
        this.setVisible(true);
    }

}
