package dunn.ted.java.instateam.dao;

import dunn.ted.java.instateam.model.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

/**
 * Created by Ted on 3/12/2017.
 */
@Repository
public class RoleDaoImpl implements RoleDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Role> findAll() {

        Session session =  sessionFactory.openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Role> criteria = builder.createQuery(Role.class);
        criteria.from(Role.class);
        List<Role> roles = session.createQuery(criteria).getResultList();

        session.close();

        return roles;
    }

    @Override
    public Role findById(Long id) {

        Session session = sessionFactory.openSession();

        Role role = session.get(Role.class, id);

        session.close();

        return role;
    }

    @Override
    public void save(Role role) {

        Session session = sessionFactory.openSession();

        session.beginTransaction();
        session.saveOrUpdate(role);
        session.getTransaction().commit();

        session.close();
    }
}
