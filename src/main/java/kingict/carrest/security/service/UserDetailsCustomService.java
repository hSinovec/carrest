package kingict.carrest.security.service;

import kingict.carrest.entity.User;
import kingict.carrest.exception.ValidationException;
import kingict.carrest.repository.UserRepository;
import kingict.carrest.security.UserDetailsCustom;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsCustomService implements UserDetailsService {

    private final UserRepository repository;

    public UserDetailsCustomService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username)
                .orElseThrow(() ->
                        new ValidationException("Ne postoji user s usernameom: " + username));

        return new UserDetailsCustom(user);
    }
}
