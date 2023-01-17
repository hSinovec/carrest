package kingict.carrest.repository.fake.impl;

import kingict.carrest.entity.CarRental;
import kingict.carrest.repository.fake.CarRentalFakeRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class CarRentalFakeRepositoryImpl implements CarRentalFakeRepository {
    private static final List<CarRental> carRentals = new ArrayList<>();
    private static Long ID = 0L;

    @Override
    public void save(CarRental carRental) {
        if (Objects.isNull(carRental.getId())) {
            carRental.setId(ID++);
            carRentals.add(carRental);
        } else {
            carRentals.stream()
                    .filter(carRental2 -> carRental2.getId().equals(carRental.getId()))
                    .findFirst()
                    .ifPresent(carRental2 -> {
                        carRental2.setDateFrom(carRental.getDateFrom());
                        carRental2.setDateTo(carRental.getDateTo());
                    });
        }
    }

    @Override
    public List<CarRental> findByDateAndCar(Long carId, LocalDate dateFrom, LocalDate dateTo) {
        return carRentals.stream()
                .filter(carRental -> carRental.getCarId().equals(carId))
                .filter(carRental -> dateFrom.isBefore(carRental.getDateTo()) || dateFrom.isEqual(carRental.getDateTo()))
                .filter(carRental -> dateTo.isAfter(carRental.getDateFrom()) || dateTo.isEqual(carRental.getDateFrom()))
                .collect(Collectors.toList());
    }

    @Override
    public List<CarRental> getAll() {
        return carRentals;
    }

    @Override
    public Optional<CarRental> findById(Long id) {
        return carRentals.stream().filter(carRental -> carRental.getId().equals(id)).findFirst();
    }

    @Override
    public void delete(Long id) {
        carRentals.removeIf(carRental -> carRental.getId().equals(id));
    }
}
