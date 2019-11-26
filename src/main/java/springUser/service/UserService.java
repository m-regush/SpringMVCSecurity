package springUser.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import springUser.dao.UserDAOImpl;
import springUser.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;

@Service
public class UserService  {

    private UserDAOImpl userHibernateDAO;

    @Autowired
    public UserService(UserDAOImpl userHibernateDAO) {
        this.userHibernateDAO = userHibernateDAO;
    }

    @Transactional
    public void addUser(User user) throws SQLException {
        String password = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(password);
        userHibernateDAO.addUser(user);
    }

    @Transactional
    public List<User> getAllUsers() throws SQLException {
        return userHibernateDAO.getAllUser();
    }

    @Transactional
    public void deleteUser(Long id) {
        userHibernateDAO.deleteUser(id);
    }

    @Transactional
    public void updateUser(User user) {
        String password = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(password);
        userHibernateDAO.updateUser(user);
    }

    @Transactional
    public User getUserByName(String name) throws SQLException {
       return userHibernateDAO.getUserByName(name);
    }

    @Transactional
    public User getUserById(Long id) throws SQLException {
        return userHibernateDAO.getUserById(id);
    }

}
