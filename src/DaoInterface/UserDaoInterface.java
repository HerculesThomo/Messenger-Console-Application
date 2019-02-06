package DaoInterface;

import Entities.User;
import java.util.List;

/**
 *
 * @author herth
 */
public interface UserDaoInterface {

    public int createUser(User user);

    public void updateUser(User user);

    public void deleteUser(String username);

    public User findUserbyUsername(String username);

    public List<User> getAllUsers();
    
    public List<String> findAllUsersName();
    
    public int getUsersId(String username);
}
