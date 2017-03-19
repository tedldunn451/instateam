package dunn.ted.java.instateam.service;

import dunn.ted.java.instateam.model.Collaborator;

import java.util.List;

/**
 * Created by Ted on 3/12/2017.
 */
public interface CollaboratorService {

    List<Collaborator> findAll();
    Collaborator findById(int id);
    void save(Collaborator collaborator);
    void delete(Collaborator collaborator);
}
