package co.com.usta.renttyj.models.services.userauth;

import co.com.usta.renttyj.entity.UserAuth;

import java.util.List;

public interface UserAuthService {

    UserAuth save(UserAuth userAuth);

    List<UserAuth> findAll();

    void delete(Long id);

    UserAuth findById(Long id) throws Exception;
}
