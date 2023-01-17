package kingict.carrest.facade.impl;

import kingict.carrest.dto.CarDto;
import kingict.carrest.entity.Car;
import kingict.carrest.facade.CarFacade;
import kingict.carrest.form.CarForm;
import kingict.carrest.service.CarService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CarFacadeImpl implements CarFacade {

    private final static List<String> VALID_SORTS = Arrays.asList("brand", "model", "price");

    private final CarService service;

    public CarFacadeImpl(CarService service) {
        this.service = service;
    }

    @Override
    public void create(CarForm carForm) {
        Car car = new Car();
        car.setBrand(carForm.getBrand());
        car.setModel(carForm.getModel());
        car.setPrice(carForm.getPrice());

//        BeanUtils.copyProperties(personForm, person);
        service.save(car);
    }

    @Override
    public CarDto get(Long carId) {
        return Optional.of(service.get(carId))
                .map(car -> {
                    CarDto dto = new CarDto();
                    dto.setBrand(car.getBrand());
                    dto.setModel(car.getModel());
                    dto.setPrice(car.getPrice());
                    return dto;
                }).orElse(null);
    }

    @Override
    public Page<CarDto> getAll(Integer pageSize, Integer pageNumber, String sort, Boolean descending) {
        if(Objects.isNull(pageSize)){
            pageSize = 10;
        }
        if(Objects.isNull(pageNumber)) {
            pageNumber = 0;
        }
        if(!VALID_SORTS.contains(sort)) {
            sort = "brand";
        }
        if(Objects.isNull(descending)){
            descending = false;
        }
        return service.getAll(pageSize, pageNumber, sort, descending)
                .map(car -> {
                    CarDto dto = new CarDto();
                    dto.setBrand(car.getBrand());
                    dto.setModel(car.getModel());
                    return dto;
                });
    }

    @Override
    public void delete(Long carId) {
        service.delete(service.get(carId));
    }

}
