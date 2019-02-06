package DaoImplementation;

import DaoInterface.UserDaoInterface;
import Entities.User;
import Database.Database;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author herth
 */
public class UserDao implements UserDaoInterface {

    @Override
    public int createUser(User user) {

        int insert = 0;
        try (Connection connection = Database.getConnection()) {
            String sql = new StringBuilder()
                    .append("INSERT INTO `users`")
                    .append("(`username`, `password`, `fname`, `lname`, `roles`, `description`)")
                    .append("VALUES(?, ?, ?, ?, ?, ?);").toString();
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, user.getUsername());
                statement.setString(2, user.getPassword());
                statement.setString(3, user.getFname());
                statement.setString(4, user.getLname());
                statement.setString(5, user.getRoles());
                statement.setString(6, user.getDescription());
                insert = statement.executeUpdate();
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return insert;
    }

    @Override
    public void updateUser(User user) {

        try (Connection connection = Database.getConnection()) {
            String sql = "UPDATE `users` SET `username` = ?, `password` = ?"
                    + ", `fname` = ?, `lname` = ?, `roles` = ?, `description` = ? WHERE `id` = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, user.getUsername());
                statement.setString(2, user.getPassword());
                statement.setString(3, user.getFname());
                statement.setString(4, user.getLname());
                statement.setString(5, user.getRoles());
                statement.setString(6, user.getDescription());
                statement.setInt(7, user.getId());
                statement.executeUpdate();
                System.out.println(user.getUsername() + "was succesfully updated");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void deleteUser(String username) {

        try (Connection connection = Database.getConnection()) {
            String sql = "DELETE FROM `users` WHERE `username` = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, username);
                statement.executeUpdate();
                System.out.println(username + " deleted");
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public User findUserbyUsername(String username) {

        try (Connection connection = Database.getConnection()) {
            String sql = "SELECT * FROM `users` WHERE `username` = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, username);
                try (ResultSet resultset = statement.executeQuery()) {
                    if (resultset.next()) {
                        User user = new User();
                        user.setId(resultset.getInt(1));
                        user.setUsername(resultset.getString(2));
                        user.setPassword(resultset.getString(3));
                        user.setFname(resultset.getString(4));
                        user.setLname(resultset.getString(5));
                        user.setRoles(resultset.getString(6));
                        user.setDescription(resultset.getString(7));
                        return user;
                    }

                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {

        List<User> users = null;
        try (Connection connection = Database.getConnection()) {
            String sql = "SELECT * FROM `users`";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                try (ResultSet resultset = statement.executeQuery()) {
                    while (resultset.next()) {
                        if (users == null) {
                            users = new ArrayList<>();
                        }
                        User user = new User();
                        user.setId(resultset.getInt(1));
                        user.setUsername(resultset.getString(2));
                        user.setPassword(resultset.getString(3));
                        user.setFname(resultset.getString(4));
                        user.setLname(resultset.getString(5));
                        user.setRoles(resultset.getString(6));
                        user.setDescription(resultset.getString(7));
                        users.add(user);
                    }
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }

    @Override
    public List<String> findAllUsersName() {

        List<String> usernames = null;
        try (Connection connection = Database.getConnection()) {
            String sql = "SELECT * FROM `users`;";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                try (ResultSet resultset = statement.executeQuery()) {
                    while (resultset.next()) {
                        if (usernames == null) {
                            usernames = new ArrayList<>();
                        }
                        String username = resultset.getString(2);
                        usernames.add(username);

                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usernames;
    }

    @Override
    public int getUsersId(String username) {

        int q = 0;
        try (Connection connection = Database.getConnection()) {
            String sql = "SELECT `id` FROM `users` WHERE `username` = ?;";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, username);
                try (ResultSet resultset = statement.executeQuery()) {
                    while (resultset.next()) {
                        String s = resultset.getString(1);
                        q = Integer.parseInt(s);

                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return q;
    }

}
