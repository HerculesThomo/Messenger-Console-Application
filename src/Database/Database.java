package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {

    private static final String Driver = "com.mysql.cj.jdbc.Driver";
    private static final String Url = "jdbc:mysql://localhost:3306/messenger?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String Username = "root";
    private static final String Password = "root";
    private static Connection connection = null;
    private static Database db;
 
    private Database() {}

    public static Database getInstance() {
        if (db == null) {
            db = new Database();
        }
        return db;
    }

    public static Connection getConnection() {

        try {
            Class.forName(Driver);
            connection = DriverManager.getConnection(Url, Username, Password);
            System.out.println("You are connected!!");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("You are not connected");
        }
        return connection;

    }
}
