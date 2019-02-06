package Entities;

/**
 *
 * @author herth
 */
public class Sent {

    private String data;
    private String to;
    private int users_id;
    private int messages_id;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
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
                + "\nto = " + to
                + "\ndata = " + data
                + "\nusers_id = " + users_id
                + "\nmessages_id = " + messages_id;
    }

}
