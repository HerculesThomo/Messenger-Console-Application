package DaoImplementation;

import DaoInterface.MessageDaoInterface;
import Entities.Message;
import Database.Database;
import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author herth
 */
public class MessageDao implements MessageDaoInterface {

    InboxDao in = new InboxDao();
    SentDao sent = new SentDao();

    @Override
    public int createMessage(Message message) {

        int reply = 0;
        try (Connection connection = Database.getConnection()) {
            String sql = new StringBuilder()
                    .append("INSERT INTO `messages`")
                    .append("(`date`, `data`, `from`, `to`)")
                    .append("VALUES (?, ?, ?, ?)").toString();
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, message.getdate());
                statement.setString(2, message.getdata());
                statement.setString(3, message.getfrom());
                statement.setString(4, message.getto());
                reply = statement.executeUpdate();
            }

        } catch (SQLException ex) {
            Logger.getLogger(MessageDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reply;
    }

    @Override
    public void updateMessage(Message message) {

        try (Connection connection = Database.getConnection()) {
            String sql = "UPDATE `messages` SET `date`= ?"
                    + ", `data` = ?, `from` = ?, `to` = ? WHERE `id` = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, message.getdate());
                statement.setString(2, message.getdata());
                statement.setString(3, message.getfrom());
                statement.setString(4, message.getto());
                statement.setInt(5, message.getid());
                statement.executeUpdate();
                System.out.println("The message was succesfully updated");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MessageDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Message> findAllMessages() {

        List<Message> messages = null;
        try (Connection connection = Database.getConnection()) {
            String sql = "SELECT * FROM `messages`";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                try (ResultSet resultset = statement.executeQuery()) {
                    while (resultset.next()) {
                        if (messages == null) {
                            messages = new ArrayList();
                        }
                        Message message = new Message();
                        message.setid(resultset.getInt(1));
                        message.setdate(resultset.getString(2));
                        message.setdata(resultset.getString(3));
                        message.setfrom(resultset.getString(4));
                        message.setto(resultset.getString(5));
                        messages.add(message);
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(MessageDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return messages;
    }
    
    public int countMessages() {
        
        int rowsAffected = 0;
        try(Connection connection = Database.getConnection()) {
            String sql = "SELECT COUNT(`id`) FROM `messages` ;";
            try(PreparedStatement statement = connection.prepareStatement(sql)) {
                try(ResultSet resultset = statement.executeQuery()) {
                    while(resultset.next()) {
                        rowsAffected = resultset.getRow();
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(MessageDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rowsAffected;
    }

}
