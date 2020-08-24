package domain;

public class User {

    private String uname;
    private String upassword;
    private Float ubalance;

    public User(){}

    public User(String uname, String upassword, Float ubalance) {
        this.uname = uname;
        this.upassword = upassword;
        this.ubalance = ubalance;
    }

    public String getUname() {
        return uname;
    }
    public void setUname(String uname) {
        this.uname = uname;
    }
    public String getUpassword() {
        return upassword;
    }
    public void setUpassword(String upassword) {
        this.upassword = upassword;
    }
    public Float getUbalance() {
        return ubalance;
    }
    public void setUbalance(Float ubalance) {
        this.ubalance = ubalance;
    }

}
