package Entities;

/**
 *
 * @author herth
 */
public class Inbox {

    private String data;
    private String froms;
    private int users_id;
    private int messages_id;

    public Inbox() {
    }

    public Inbox(String data, String froms, int users_id, int messages_id) {
        this.data = data;
        this.froms = froms;
        this.users_id = users_id;
        this.messages_id = messages_id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getFrom() {
        return froms;
    }

    public void setFrom(String from) {
        this.froms = from;
    }

    public int getUsers_id() {
        return users_id;
    }

    public void setUsers_id(int users_id) {
        this.users_id = users_id;
    }

    public int getMessages_id() {
        return messages_id;
    }

    public void setMessages_id(int messages_id) {
        this.messages_id = messages_id;
    }

    @Override
    public String toString() {
        return " data = " + data
                + "\nfrom = " + froms
                + "\nusers_id = " + users_id
                + "\nmessages_id = " + messages_id;
    }

}
