//车票
public class Ticket {

    private String start;//起始站名
    private String end;//终点站名
    private Float price;//票价

    public Ticket(){}
    public Ticket(String start, String end, Float price){
        this.start = start;
        this.end = end;
        this.price = price;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder("[ ");
        builder.append(this.start);
        builder.append(" --> ");
        builder.append(this.end);
        builder.append(" : ");
        builder.append(this.price);
        builder.append(" ]");
        return builder.toString();
    }

    public String getStart() {
        return start;
    }
    public void setStart(String start) {
        this.start = start;
    }
    public String getEnd() {
        return end;
    }
    public void setEnd(String end) {
        this.end = end;
    }
    public Float getPrice() {
        return price;
    }
    public void setPrice(Float price) {
        this.price = price;
    }
}
