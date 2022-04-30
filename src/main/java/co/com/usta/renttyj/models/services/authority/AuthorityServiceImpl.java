package co.com.usta.renttyj.models.services.authority;

import co.com.usta.renttyj.entity.Authority;
import co.com.usta.renttyj.handler.exceptions.CustomException;
import co.com.usta.renttyj.models.daos.AuthorityDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorityServiceImpl implements AuthorityService {

    final AuthorityDAO repository;

    @Override
    public Authority save(Authority authority) {
        if (authority.getId() == null) {
            authority.setIsActive(true);
        }
        return repository.save(authority);
    }

    @Override
    public List<Authority> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Long id) {
        repository.delete(findById(id));
    }

    @Override
    public Authority findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new CustomException(
                        String.format("El permiso con código %s no fue encontrado", id)));
    }
}
