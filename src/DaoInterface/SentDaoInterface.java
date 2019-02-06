package DaoInterface;

import Entities.Sent;
import java.util.List;

/**
 *
 * @author herth
 */
public interface SentDaoInterface {

    public void deleteSentMessage(String to);

    public Sent findSentMessage(int users_id, String to);

    public List<Sent> findAllSentMessages(int users_id);

    public void savedToSent(String data, String to, int users_id, int messages_id);

    public void updateSentMessage(String data, int users_id, String to);

}
