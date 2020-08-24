package domain;

import java.util.Objects;

public class Commodity {

    private Integer cid;
    private String cname;
    private Float cprice;
    private Integer ccount;
    private Kind kind;//外键

    public Commodity(){}

    public Commodity(Integer cid, String cname, Float cprice, Integer ccount, Kind kind) {
        this.cid = cid;
        this.cname = cname;
        this.cprice = cprice;
        this.ccount = ccount;
        this.kind = kind;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Commodity commodity = (Commodity) o;
        return cid.equals(commodity.cid) &&
                kind.getKid().equals(commodity.kind.getKid());
    }

    @Override
    public int hashCode() {
        return Objects.hash(cid, kind.getKid());
    }

    public Integer getCid() {
        return cid;
    }
    public void setCid(Integer cid) {
        this.cid = cid;
    }
    public String getCname() {
        return cname;
    }
    public void setCname(String cname) {
        this.cname = cname;
    }
    public Float getCprice() {
        return cprice;
    }
    public void setCprice(Float cprice) {
        this.cprice = cprice;
    }
    public Kind getKind() {
        return kind;
    }
    public void setKind(Kind kind) {
        this.kind = kind;
    }
    public Integer getCcount() {
        return ccount;
    }
    public void setCcount(Integer ccount) {
        this.ccount = ccount;
    }
}
