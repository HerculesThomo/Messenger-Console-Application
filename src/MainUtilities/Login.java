package MainUtilities;

import java.util.Scanner;
import Database.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author herth
 */
public class Login {

    private static String username;
    private static String password;
    Scanner scan;

    public Login() {
        scan = new Scanner(System.in);
    }

    public Login(String username, String password) {
        Login.username = username;
        Login.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        Login.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        Login.password = password;
    }

    public boolean isUser(String username, String password) {
        
        try {
            Connection connection = Database.getConnection();
            String sql = "SELECT * FROM `messenger`.`users` WHERE username = ?"
                    + " AND password = ? ;";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, username);
                statement.setString(2, password);
                try (ResultSet resultset = statement.executeQuery()) {
                    if (resultset.first()) {

                        return true;
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
