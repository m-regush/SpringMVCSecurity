package springUser.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springUser.dao.UserHibernate;
import springUser.dao.UserHibernateDAO;
import springUser.model.User;
import springUser.model.UserRole;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserDetail implements UserDetailsService {

    private UserHibernateDAO userHibernateDAO;


    @Autowired
    public UserDetail(UserHibernateDAO userHibernateDAO) {
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

        if (user != null) {
            Collection<GrantedAuthority> authorities = user.getUserRoles()
                    .stream()
                    .map(userRole -> new SimpleGrantedAuthority(userRole.getRole()))
                    .collect(Collectors.toCollection(HashSet::new));
            return new org.springframework.security.core.userdetails.User(name, user.getPassword(),
                    true, true, true, true, authorities);
        } else {
            throw new UsernameNotFoundException("User Not Found");
        }
}
}
