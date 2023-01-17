package kingict.carrest.service.impl;

import kingict.carrest.entity.Car;
import kingict.carrest.repository.CarRentalRepository;
import kingict.carrest.repository.CarRepository;
import kingict.carrest.service.CarService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository repository;
    private final CarRentalRepository carRentalRepository;

    public CarServiceImpl(CarRepository repository,
                          CarRentalRepository carRentalRepository) {
        this.repository = repository;
        this.carRentalRepository = carRentalRepository;
    }

    @Override
    public void save(Car car) {
        repository.save(car);
    }

    @Override
    public Car get(Long carId) {
        return repository.findById(carId)
                .orElseThrow(() -> new IllegalArgumentException("Nema osobe s tim id-em: " + carId));
    }

    @Override
    public Page<Car> getAll(Integer pageSize, Integer pageNumber, String sort, Boolean descending) {
        Sort sortBy = Sort.by(sort);
        if (Boolean.TRUE.equals(descending)) {
            sortBy = sortBy.descending();
        }
        return repository.findAll(PageRequest.of(pageNumber, pageSize, sortBy));
    }

    @Override
    public void delete(Car car) {
        repository.delete(car);
    }

}
