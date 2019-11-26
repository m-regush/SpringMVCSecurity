package springUser.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import springUser.dao.UserDAOImpl;
import springUser.model.User;

import java.sql.SQLException;

@Service
public class UserDetail implements UserDetailsService {

    private UserDAOImpl userHibernateDAO;

    @Autowired
    public UserDetail(UserDAOImpl userHibernateDAO) {
        this.userHibernateDAO = userHibernateDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = null;
        try {
            user = userHibernateDAO.getUserByName(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}

