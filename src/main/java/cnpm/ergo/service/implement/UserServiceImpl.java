package cnpm.ergo.service.implement;

import cnpm.ergo.DAO.implement.UserDAOImpl;
import cnpm.ergo.DAO.interfaces.IUserDAO;
import cnpm.ergo.entity.User;
import cnpm.ergo.service.interfaces.IUserService;

import java.util.List;

public class UserServiceImpl implements IUserService {
    // Create
    public boolean addUser(User user) {
        IUserDAO IUserDAO = new UserDAOImpl();
        return IUserDAO.addUser(user);
    }

    // Read
    public List<User> getAllUsers() {
        IUserDAO IUserDAO = new UserDAOImpl();
        return IUserDAO.getAllUsers();
    }

    public User getUserById(int userId) {
        IUserDAO IUserDAO = new UserDAOImpl();
        return IUserDAO.getUserById(userId);
    }

    // Update
    public boolean updateUser(User user) {
        IUserDAO IUserDAO = new UserDAOImpl();
        return IUserDAO.updateUser(user);
    }

    // Delete
    public boolean deleteUser(int userId) {
        IUserDAO IUserDAO = new UserDAOImpl();
        return IUserDAO.deleteUser(userId);
    }

    public static void main(String[] args) {

    }
}
