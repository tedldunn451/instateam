package dunn.ted.java.instateam.dao;

import dunn.ted.java.instateam.model.Collaborator;
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
public class CollaboratorDaoImpl implements CollaboratorDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Collaborator> findAll() {

        Session session =  sessionFactory.openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Collaborator> criteria = builder.createQuery(Collaborator.class);
        criteria.from(Collaborator.class);
        List<Collaborator> collaborators = session.createQuery(criteria).getResultList();

        session.close();

        return collaborators;
    }

    @Override
    public Collaborator findById(Long id) {

        Session session = sessionFactory.openSession();

        Collaborator collaborator = session.get(Collaborator.class, id);

        session.close();

        return collaborator;
    }

    @Override
    public void save(Collaborator collaborator) {

        Session session = sessionFactory.openSession();

        session.beginTransaction();
        session.saveOrUpdate(collaborator);
        session.getTransaction().commit();

        session.close();
    }

    @Override
    public void delete(Collaborator collaborator) {

        Session session = sessionFactory.openSession();

        session.beginTransaction();
        session.delete(collaborator);
        session.getTransaction().commit();

        session.close();
    }
}
