package kingict.carrest.controller;


import kingict.carrest.entity.User;
import kingict.carrest.facade.UserFacade;
import kingict.carrest.form.PersonForm;
import kingict.carrest.form.UserForm;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserFacade facade;

    public UserController(UserFacade facade) {
        this.facade = facade;
    }

    @PostMapping
    public void create(@Valid @NotNull @RequestBody UserForm userForm) {
        facade.create(userForm);
    }

}
