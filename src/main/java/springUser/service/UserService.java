package springUser.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import springUser.dao.UserHibernateDAO;
import springUser.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springUser.model.UserRole;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService  {

    private UserHibernateDAO userHibernateDAO;


    @Autowired
    public UserService(UserHibernateDAO userHibernateDAO) {
        this.userHibernateDAO = userHibernateDAO;
    }


    @Transactional
    public void addUser(User user) throws SQLException {
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
