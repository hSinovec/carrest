package kingict.carrest.controller;

import kingict.carrest.dto.PersonDto;


import kingict.carrest.dto.RentalPersonDto;
import kingict.carrest.entity.Person;
import kingict.carrest.facade.PersonFacade;
import kingict.carrest.form.PersonForm;
import kingict.carrest.security.CurrentUser;
import kingict.carrest.security.UserDetailsCustom;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

    private final PersonFacade facade;

    public PersonController(PersonFacade facade) {
        this.facade = facade;
    }

    //get one
    @GetMapping("/{personId}")
    public PersonDto get(@PathVariable Long personId, @CurrentUser UserDetailsCustom userDetails) {
        System.out.println(userDetails.getUsername() + userDetails.getPassword());
        return facade.get(personId);
    }

    //get all
    @GetMapping("/all")
    public Page<PersonDto> getAll(@RequestParam(required = false) Integer pageSize,
                                  @RequestParam(required = false) Integer pageNumber,
                                  @RequestParam(required = false) String sort,
                                  @RequestParam(required = false) Boolean descending) {
        return facade.getAll(pageSize, pageNumber, sort, descending);
    }

    //post - create
    @PostMapping
    public void create(@RequestBody PersonForm personForm) {
        facade.create(personForm);
    }

    //put - azuriranje
    @PutMapping
    public void edit() {
    }
    //delete
    @DeleteMapping("/{personId}")
    public void delete(@PathVariable Long personId) {
        facade.delete(personId);
    }
    //get car rental count

    @GetMapping("/{personId}/car-rental-count")
    public RentalPersonDto getCarRentalCount(@PathVariable Long personId){
        return facade.getCarRentalCount(personId);
    }
}
