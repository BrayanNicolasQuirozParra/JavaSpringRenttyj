package co.com.usta.renttyj.models.services.role;

import co.com.usta.renttyj.entity.Role;

import java.util.List;

public interface RoleService {

    Role save(Role role);

    List<Role> findAll();

    void delete(Long id);

    Role findById(Long id) throws Exception;
}
