package dunn.ted.java.instateam.dao;

import dunn.ted.java.instateam.model.Project;
import org.hibernate.Hibernate;
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
public class ProjectDaoImpl implements ProjectDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Project> findAll() {

        Session session =  sessionFactory.openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Project> criteria = builder.createQuery(Project.class);
        criteria.from(Project.class);
        List<Project> projects = session.createQuery(criteria).getResultList();

        session.close();

        return projects;
    }

    @Override
    public Project findById(Long id) {

        Session session = sessionFactory.openSession();

        Project project = session.get(Project.class, id);
        Hibernate.initialize(project.getCollaborators());
        Hibernate.initialize(project.getRolesNeeded());

        session.close();

        return project;
    }

    @Override
    public void save(Project project) {

        Session session = sessionFactory.openSession();

        session.beginTransaction();
        session.saveOrUpdate(project);
        session.getTransaction().commit();

        session.close();
    }
}
