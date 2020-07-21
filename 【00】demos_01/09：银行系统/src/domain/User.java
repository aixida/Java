package domain;

public class User {

    //只是为了记录数据库中的一行信息   账号 密码 余额
    private String aname;
    private String apassword;
    private Float abalance;

    public User(){}

    public User(String aname, String apassword, Float abalance) {
        this.aname = aname;
        this.apassword = apassword;
        this.abalance = abalance;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public String getApassword() {
        return apassword;
    }

    public void setApassword(String apassword) {
        this.apassword = apassword;
    }

    public Float getAbalance() {
        return abalance;
    }

    public void setAbalance(Float abalance) {
        this.abalance = abalance;
    }
}
