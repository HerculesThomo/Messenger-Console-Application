package DaoImplementation;
import DaoInterface.SentDaoInterface;
import Database.Database;
import Entities.Sent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author herth
 */
public class SentDao implements SentDaoInterface{

    @Override
    public void deleteSentMessage(String to) {

        try (Connection connection = Database.getConnection()) {
            String sql = "DELETE FROM `sent` WHERE  `to`= ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, to);
                statement.executeUpdate();
                System.out.println("The message" + to + "was deleted");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MessageDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Sent findSentMessage(int users_id, String to) {

        try (Connection connection = Database.getConnection()) {
            String sql = "SELECT * FROM `sent` WHERE `users_id` = ? AND `to` = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, users_id);
                statement.setString(2, to);
                try (ResultSet resultset = statement.executeQuery()) {
                    if (resultset.next()) {
                        Sent sent = new Sent();
                        sent.setData(resultset.getString(1));
                        sent.setTo(resultset.getString(2));
                        sent.setUsers_id(resultset.getInt(3));
                        sent.setMessages_id(resultset.getInt(4));
                        return sent;
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(MessageDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Sent> findAllSentMessages(int users_id) {

        List<Sent> messages = null;
        try (Connection connection = Database.getConnection()) {
            String sql = "SELECT * FROM `sent` WHERE `users_id` = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, users_id);
                try (ResultSet resultset = statement.executeQuery()) {
                    while (resultset.next()) {
                        if (messages == null) {
                            messages = new ArrayList();
                        }
                        Sent sent = new Sent();
                        sent.setData(resultset.getString(1));
                        sent.setTo(resultset.getString(2));
                        sent.setUsers_id(users_id);
                        sent.setMessages_id(resultset.getInt(4));
                        messages.add(sent);
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(MessageDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return messages;
    }

    @Override
    public void savedToSent(String data, String to, int users_id, int messages_id) {

        try (Connection connection = Database.getConnection()) {
            String sql = new StringBuilder()
                    .append("INSERT INTO `messenger`.`sent`")
                    .append("(`data`, `to`, `users_id`, `messages_id`)")
                    .append("VALUES (?, ?, ?, ?)").toString();
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, data);
                statement.setString(2, to);
                statement.setInt(3, users_id);
                statement.setInt(4, messages_id);
                statement.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(MessageDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void updateSentMessage(String data, int users_id, String to) {

        try (Connection connection = Database.getConnection()) {
            String sql = "UPDATE `sent` SET `data`= ?, `users_id` = ? WHERE `to` = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, data);
                statement.setInt(2, users_id);
                statement.setString(3, to);
                statement.executeUpdate();
                System.out.println("The message was succesfully updated");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MessageDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
