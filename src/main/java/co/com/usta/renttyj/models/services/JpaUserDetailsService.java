package co.com.usta.renttyj.models.services;

import co.com.usta.renttyj.entity.UserAuth;
import co.com.usta.renttyj.handler.exceptions.CustomException;
import co.com.usta.renttyj.models.daos.UserAuthDAO;
import co.com.usta.renttyj.security.models.UserDetailsApp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static co.com.usta.renttyj.utils.Constants.USER_BY_USERNAME_NOT_FOUND;

@Service("jpaUserDetailsService")
@RequiredArgsConstructor
@Slf4j
public class JpaUserDetailsService implements UserDetailsService {

    private final UserAuthDAO userAuthDAO;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAuth userAuth = userAuthDAO.findUserAuthByUsername(username)
                .orElseThrow(() -> new CustomException(String.format(USER_BY_USERNAME_NOT_FOUND, username)));
        userAuth.setLastLogin(userAuth.getLastLogin());
        userAuth.setLastLoginDisplay(LocalDateTime.now());
        return new UserDetailsApp(userAuthDAO.save(userAuth));
    }

}
