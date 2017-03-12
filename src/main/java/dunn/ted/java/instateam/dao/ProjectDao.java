package dunn.ted.java.instateam.dao;

import dunn.ted.java.instateam.model.Project;

import java.util.List;

/**
 * Created by Ted on 3/12/2017.
 */
public interface ProjectDao {
    List<Project> findAll();
    Project findById(Long id);
    void save(Project project);
}
