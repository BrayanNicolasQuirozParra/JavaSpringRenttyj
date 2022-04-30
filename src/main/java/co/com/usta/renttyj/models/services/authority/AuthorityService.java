package co.com.usta.renttyj.models.services.authority;

import co.com.usta.renttyj.entity.Authority;

import java.util.List;

public interface AuthorityService {

    Authority save(Authority authority);

    List<Authority> findAll();

    void delete(Long id);

    Authority findById(Long id) throws Exception;
}
