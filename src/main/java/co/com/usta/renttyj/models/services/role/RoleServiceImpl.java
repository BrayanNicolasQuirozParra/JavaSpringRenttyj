package co.com.usta.renttyj.models.services.role;

import co.com.usta.renttyj.entity.Role;
import co.com.usta.renttyj.handler.exceptions.CustomException;
import co.com.usta.renttyj.models.daos.RoleDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    final RoleDAO repository;

    @Override
    public Role save(Role role) {
        if (role.getId() == null) {
            role.setIsActive(true);
        }
        return repository.save(role);
    }

    @Override
    public List<Role> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Long id) {
        repository.delete(findById(id));
    }

    @Override
    public Role findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new CustomException(
                        String.format("El rol con código %s no fue encontrado", id)));
    }
}
