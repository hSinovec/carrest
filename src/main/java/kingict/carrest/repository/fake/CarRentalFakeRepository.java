package kingict.carrest.repository.fake;
import kingict.carrest.entity.CarRental;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CarRentalFakeRepository {

    void save(CarRental carRental);
    List<CarRental> findByDateAndCar(Long carId, LocalDate dateFrom, LocalDate dateTo);

    List<CarRental> getAll();

    Optional<CarRental> findById(Long id);

    void delete(Long id);
}
