package kingict.carrest.facade.impl;

import kingict.carrest.entity.User;
import kingict.carrest.facade.UserFacade;
import kingict.carrest.form.UserForm;
import kingict.carrest.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserFacadeImpl implements UserFacade {

    private final UserService service;
    private final PasswordEncoder passwordEncoder;

    public UserFacadeImpl(UserService service, PasswordEncoder passwordEncoder) {
        this.service = service;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void create(UserForm userForm) {
        User user = new User();
        user.setFirstname(userForm.getFirstname());
        user.setLastname(userForm.getLastname());
        user.setUsername(userForm.getUsername().toLowerCase());
        user.setPassword(passwordEncoder.encode(userForm.getPassword()));
        user.setRoles("VIEWER");
        service.save(user);
    }
}
