package dunn.ted.java.instateam.service;

import dunn.ted.java.instateam.dao.ProjectDao;
import dunn.ted.java.instateam.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Ted on 3/12/2017.
 */
@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectDao projectDao;

    @Override
    public List<Project> findAll() {
        return projectDao.findAll();
    }

    @Override
    public Project findById(int id) {
        return projectDao.findById(id);
    }

    @Override
    public void save(Project project) {
        projectDao.save(project);
    }

    @Override
    public void delete(Project project) { projectDao.delete(project); }
}
