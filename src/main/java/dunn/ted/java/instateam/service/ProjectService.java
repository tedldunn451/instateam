package dunn.ted.java.instateam.service;

import dunn.ted.java.instateam.model.Project;

import java.util.List;

/**
 * Created by Ted on 3/12/2017.
 */
public interface ProjectService {

    List<Project> findAll();
    Project findById(int id);
    void save(Project project);
}
