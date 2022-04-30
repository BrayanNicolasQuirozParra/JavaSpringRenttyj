package co.com.usta.renttyj.models.services.userauth;

import co.com.usta.renttyj.entity.UserAuth;
import co.com.usta.renttyj.handler.exceptions.CustomException;
import co.com.usta.renttyj.models.daos.UserAuthDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserAuthServiceImpl implements UserAuthService {

    final UserAuthDAO repository;

    @Override
    public UserAuth save(UserAuth userAuth) {
        if (userAuth.getId() == null) {
            userAuth.setProfileImageUrl("https://robohash.org/" + userAuth.getUsername());
            userAuth.setIsActive(true);
            userAuth.setIsNotLocked(true);
        }
        return repository.save(userAuth);
    }

    @Override
    public List<UserAuth> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Long id) {
        repository.delete(findById(id));
    }

    @Override
    public UserAuth findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new CustomException(
                        String.format("El usuario con código %s no fue encontrado", id)));
    }
}
