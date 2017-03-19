package dunn.ted.java.instateam.dao;

import dunn.ted.java.instateam.model.Role;

import java.util.List;

/**
 * Created by Ted on 3/12/2017.
 */
public interface RoleDao {
    List<Role> findAll();
    Role findById(Long id);
    void save(Role role);
    void delete(Role role);
}
