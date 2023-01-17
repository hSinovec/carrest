package kingict.carrest.service.impl;

import kingict.carrest.entity.User;
import kingict.carrest.repository.UserRepository;
import kingict.carrest.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public void save(User user) {
        if(repository.findByUsername(user.getUsername()).isEmpty()){
            repository.save(user);
        }
        else
            throw new RuntimeException("User already exists" + user.getUsername());
    }
}
