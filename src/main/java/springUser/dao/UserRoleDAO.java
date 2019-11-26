package springUser.dao;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import springUser.model.User;
import springUser.model.UserRole;


import java.sql.SQLException;

@Repository
public class UserRoleDAO {

    public SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public UserRole getUserRole(String role) throws SQLException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("FROM UserRole WHERE role= :role");
        query.setParameter("role", role);
        UserRole userRole = (UserRole) query.uniqueResult();
        transaction.commit();
        session.close();
        return userRole;
    }

    public UserRole getRoleById(Long id) throws SQLException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("FROM UserRole WHERE id= :id");
        query.setParameter("id", id);
        UserRole userRole = (UserRole) query.uniqueResult();
        transaction.commit();
        session.close();
        return userRole;
    }
}
