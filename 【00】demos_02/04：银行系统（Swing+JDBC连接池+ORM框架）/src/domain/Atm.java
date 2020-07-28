package domain;

public class Atm {
    //实体对象  每一个Atm类的对象存储 Atm表格中一行记录
    //有可能当前记录中含有null值

    private String aname;
    private String apassword;
    private Float abalance;

    public Atm() {
    }

    public Atm(String aname, String apassword, Float abalance) {
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
