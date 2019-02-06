package Entities;

/**
 *
 * @author herth
 */
public class User {

    private Integer id;
    private String username;
    private String password;
    private String fname;
    private String lname;
    private String roles;
    private String description;

    public User() {
    }

    public User(String username, String password, String fname, String lname, String roles, String description) {
        this.username = username;
        this.password = password;
        this.fname = fname;
        this.lname = lname;
        this.roles = roles;
        this.description = description;
    }

    public User(Integer id, String username, String password, String fname, String lname, String roles, String description) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fname = fname;
        this.lname = lname;
        this.roles = roles;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return " id = " + id
                + "\n username = " + username + "\n password = "
                + password + "\n first name = " + fname
                + "\n last name = " + lname
                + "\n role = " + roles
                + "\n description = " + description;
    }

}
