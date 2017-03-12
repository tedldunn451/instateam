package dunn.ted.java.instateam.service;

import dunn.ted.java.instateam.dao.RoleDao;
import dunn.ted.java.instateam.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Ted on 3/12/2017.
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public Role findById(Long id) {
        return roleDao.findById(id);
    }

    @Override
    public void save(Role role) {
        roleDao.save(role);
    }
}
