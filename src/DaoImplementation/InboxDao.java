package DaoImplementation;

import DaoInterface.InboxDaoInterface;
import Entities.Inbox;
import Database.Database;
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
public class InboxDao implements InboxDaoInterface{

    @Override
    public void deleteInboxMessage(String froms) {

        try (Connection connection = Database.getConnection()) {
            String sql = "DELETE FROM `inbox` WHERE  `froms` = ?;";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, froms);
                statement.executeUpdate();
                System.out.println("The message" + froms + "was deleted");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MessageDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Inbox> findAllInboxMessages(int users_id) {

        int q = 0;
        List<Inbox> messages = null;
        try (Connection connection = Database.getConnection()) {

            String sql = "SELECT * FROM `inbox` WHERE `users_id` = ? ";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, users_id);
                try (ResultSet resultset = statement.executeQuery()) {
                    while (resultset.next()) {
                        if (messages == null) {
                            messages = new ArrayList();
                        }
                        Inbox inbox = new Inbox();
                        inbox.setData(resultset.getString(2));
                        inbox.setFrom(resultset.getString(3));
                        inbox.setUsers_id(resultset.getInt(4));
                        inbox.setMessages_id(resultset.getInt(5));
                        messages.add(inbox);
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(MessageDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return messages;
    }

    @Override
    public void savedToInbox(String data, String froms, int users_id, int messages_id) {

        try (Connection connection = Database.getConnection()) {
            String sql = new StringBuilder()
                    .append("INSERT INTO `messenger`.`inbox`")
                    .append("(`data`, `froms`, `users_id`, `messages_id`)")
                    .append("VALUES (?, ?, ?, ?)").toString();
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, data);
                statement.setString(2, froms);
                statement.setInt(3, users_id);
                statement.setInt(4, messages_id);
                statement.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(MessageDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void updateInboxMessage(String data, String from, int users_id) {

        try (Connection connection = Database.getConnection()) {
            String sql = "UPDATE `inbox` SET `data`= ?, `users_id` = ? WHERE `froms` = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, data);
                statement.setInt(2, users_id);
                statement.setString(3, from);
                statement.executeUpdate();
                System.out.println("The message was succesfully updated");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MessageDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Inbox> getAllInboxMessages() {

        int q = 0;
        List<Inbox> messages = null;
        try (Connection connection = Database.getConnection()) {
            String sql = "SELECT * FROM `inbox` ; ";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                try (ResultSet resultset = statement.executeQuery()) {
                    while (resultset.next()) {
                        if (messages == null) {
                            messages = new ArrayList();
                        }
                        Inbox inbox = new Inbox();
                        inbox.setData(resultset.getString(2));
                        inbox.setFrom(resultset.getString(3));
                        inbox.setUsers_id(resultset.getInt(4));
                        inbox.setMessages_id(resultset.getInt(5));
                        messages.add(inbox);
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(MessageDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return messages;
    }

}
