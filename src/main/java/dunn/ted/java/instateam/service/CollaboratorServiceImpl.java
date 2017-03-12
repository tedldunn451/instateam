package dunn.ted.java.instateam.service;

import dunn.ted.java.instateam.dao.CollaboratorDao;
import dunn.ted.java.instateam.model.Collaborator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Ted on 3/12/2017.
 */
@Service
public class CollaboratorServiceImpl implements CollaboratorService {

    @Autowired
    private CollaboratorDao collaboratorDao;

    @Override
    public List<Collaborator> findAll() {
        return collaboratorDao.findAll();
    }

    @Override
    public Collaborator findById(Long id) {
        return collaboratorDao.findById(id);
    }

    @Override
    public void save(Collaborator collaborator) {
        collaboratorDao.save(collaborator);
    }
}
