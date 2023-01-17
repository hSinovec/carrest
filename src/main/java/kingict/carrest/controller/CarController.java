package kingict.carrest.controller;

import kingict.carrest.dto.CarDto;
import kingict.carrest.facade.CarFacade;
import kingict.carrest.form.CarForm;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    private final CarFacade facade;

    public CarController(CarFacade facade) {
        this.facade = facade;
    }

    //get one
    @GetMapping("/{carId}")
    public CarDto get(@PathVariable Long carId) {
        return facade.get(carId);
    }

    //get all
    @GetMapping("/all")
    public Page<CarDto> getAll(@RequestParam(required = false) Integer pageSize,
                               @RequestParam(required = false) Integer pageNumber,
                               @RequestParam(required = false) String sort,
                               @RequestParam(required = false) Boolean descending) {
        return facade.getAll(pageSize, pageNumber, sort, descending);
    }

    //post - create
    @PostMapping
    public void create(@RequestBody CarForm carForm) {
        facade.create(carForm);
    }

    //put - azuriranje
    @PutMapping
    public void edit() {

    }
    //delete
    @DeleteMapping("/{carId}")
    public void delete(@PathVariable Long carId) {
        facade.delete(carId);
    }
    
}
