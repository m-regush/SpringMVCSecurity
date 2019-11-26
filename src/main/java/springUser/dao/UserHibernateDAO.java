package springUser.dao;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import springUser.model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.xml.namespace.QName;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserHibernateDAO implements UserHibernate {

    private SessionFactory sessionFactory;


    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }



    public List<User> getAllUser() throws SQLException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<User> users = session.createQuery("FROM User").list();
        transaction.commit();
        session.close();
        return users;
    }


    public void addUser(User user) throws SQLException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String password = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(password);
        session.save(user);
        transaction.commit();
        session.close();
    }


    public void deleteUser(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        User user = (User) session.get(User.class, id);
        session.delete(user);
        transaction.commit();
        session.close();
    }


    public void updateUser(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String password = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(password);
        session.update(user);
        transaction.commit();
        session.close();
    }


    public User getUserByName(String name) throws SQLException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("FROM User WHERE name= :name");
        query.setParameter("name", name);
        User user = (User) query.uniqueResult();
        transaction.commit();
        session.close();
        return user;
    }


    public User getUserById(Long id) throws SQLException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("FROM User WHERE id= :id");
        query.setParameter("id", id);
        User user = (User) query.uniqueResult();
        transaction.commit();
        session.close();
        return user;
    }


}
