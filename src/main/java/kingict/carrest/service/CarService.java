package kingict.carrest.service;

import kingict.carrest.entity.Car;
import kingict.carrest.entity.Person;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CarService {

    void save(Car car);

    Car get(Long carId);

    Page<Car> getAll(Integer pageSize, Integer pageNumber, String sort, Boolean descending);

    void delete(Car car);
}
