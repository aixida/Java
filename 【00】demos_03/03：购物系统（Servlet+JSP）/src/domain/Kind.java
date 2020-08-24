package domain;

public class Kind {

    private Integer kid;
    private String kname;

    public Kind() {}
    public Kind(Integer kid, String kname) {
        this.kid = kid;
        this.kname = kname;
    }

    public Integer getKid() {
        return kid;
    }
    public void setKid(Integer kid) {
        this.kid = kid;
    }
    public String getKname() {
        return kname;
    }
    public void setKname(String kname) {
        this.kname = kname;
    }
}
