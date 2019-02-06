package Entities;

/**
 *
 * @author herth
 */
public class Message {

    private Integer id;
    private String date;
    private String data;
    private String from;
    private String to;

    public Message() {

    }

    public Message(String date, String data, String from, String to) {
        this.date = date;
        this.data = data;
        this.from = from;
        this.to = to;
    }
    
    public Message(Integer id, String date, String data, String from, String to) {
        this.id = id;
        this.date = date;
        this.data = data;
        this.from = from;
        this.to = to;
    }

    public Integer getid() {
        return id;
    }

    public void setid(Integer id) {
        this.id = id;
    }

    public String getdate() {
        return date;
    }

    public void setdate(String date) {
        this.date = date;
    }

    public String getdata() {
        return data;
    }

    public void setdata(String data) {
        this.data = data;
    }

    public String getfrom() {
        return from;
    }

    public void setfrom(String from) {
        this.from = from;
    }

    public String getto() {
        return to;
    }

    public void setto(String to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return " id = " + id
                + "\ndate = " + date
                + "\ndata = " + data
                + "\nfrom = " + from
                + "\nto = " + to;
    }
}
