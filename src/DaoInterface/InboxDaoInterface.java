package DaoInterface;

import Entities.Inbox;
import java.util.List;

/**
 *
 * @author herth
 */
public interface InboxDaoInterface {

    public void deleteInboxMessage(String froms);

    public List<Inbox> findAllInboxMessages(int users_id);

    public void savedToInbox(String data, String froms, int users_id, int messages_id);

    public void updateInboxMessage(String data, String from, int users_id);

    public List<Inbox> getAllInboxMessages();

}
