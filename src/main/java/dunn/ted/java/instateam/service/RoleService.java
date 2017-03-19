package dunn.ted.java.instateam.service;

import dunn.ted.java.instateam.model.Role;

import java.util.List;

/**
 * Created by Ted on 3/12/2017.
 */
public interface RoleService {

    List<Role> findAll();
    Role findById(int id);
    void save(Role role);
    void delete(Role role);

}
