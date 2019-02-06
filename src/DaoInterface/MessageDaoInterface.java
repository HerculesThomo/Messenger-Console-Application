package DaoInterface;

import Entities.Message;
import java.util.List;

/**
 *
 * @author herth
 */
public interface MessageDaoInterface {

    public int createMessage(Message message);

    public void updateMessage(Message message);

    public List<Message> findAllMessages();

}
