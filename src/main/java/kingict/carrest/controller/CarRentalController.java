package kingict.carrest.controller;



import javax.validation.Valid;
import kingict.carrest.dto.CarRentalDto;
import kingict.carrest.dto.RentalPersonDto;
import kingict.carrest.dto.TotalPriceDto;
import kingict.carrest.facade.CarRentalFacade;
import kingict.carrest.form.CarRentalForm;
import kingict.carrest.form.CarRentalEditForm;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/car-rentals")
public class CarRentalController {

    private final CarRentalFacade facade;

    public CarRentalController(CarRentalFacade facade) {
        this.facade = facade;
    }

    @PostMapping
//    @Secured("ROLE_EDITOR", "ROLE_VIEWER')
    @PreAuthorize("hasRole('EDITOR') or hasRole('VIEWER')")
    public @ResponseStatus(HttpStatus.CREATED)
    void create(@Valid @RequestBody CarRentalForm form) {
        facade.create(form);
    }

    @GetMapping
    public Page<CarRentalDto> getAll(@RequestParam(required = false) Integer pageSize,
                                     @RequestParam(required = false) Integer pageNumber,
                                     @RequestParam(required = false) String sort,
                                     @RequestParam(required = false) Boolean descending) {
        return facade.getAll(pageSize, pageNumber, sort, descending);
    }

    @PutMapping("/{id}")
    public void edit(@RequestBody CarRentalEditForm form, @PathVariable Long id) {
        facade.edit(id, form);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        facade.delete(id);
    }

    @GetMapping("/person/{personId}")
    public RentalPersonDto getPersonRentals(@PathVariable Long personId){
        return facade.getPersonRentals(personId);
    }

    @GetMapping("/person/{personId}/total-price")
    public List <TotalPriceDto> getPersonRentalsPrice(@PathVariable Long personId){
        return facade.getTotalPriceInCarRentalsFromPersonId(personId);
    }
}
