package domain;

public class City {

    private Integer cityid;
    private String cityname;
    private Integer citysize;
    private Area area;//外键----关联Area地区表格的对象

    public City() {
    }

    public City(Integer cityid, String cityname, Integer citysize, Area area) {
        this.cityid = cityid;
        this.cityname = cityname;
        this.citysize = citysize;
        this.area = area;
    }

    public Integer getCityid() {
        return cityid;
    }

    public void setCityid(Integer cityid) {
        this.cityid = cityid;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public Integer getCitysize() {
        return citysize;
    }

    public void setCitysize(Integer citysize) {
        this.citysize = citysize;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }
}
