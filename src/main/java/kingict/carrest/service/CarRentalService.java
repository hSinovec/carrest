package kingict.carrest.service;

import kingict.carrest.dto.RentalPersonDto;
import kingict.carrest.entity.CarRental;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface CarRentalService {
    void save(CarRental carRental);

    Page<CarRental> getAll(Integer pageSize, Integer pageNumber, String sort, Boolean descending);

    List<CarRental> get(Long carId, LocalDate dateFrom, LocalDate dateTo);

    CarRental get(Long id);

    void delete(CarRental carRental);

    RentalPersonDto getPersonRentals(Long personId);

    public List<CarRental> getAllRentalsOnPerson(Long personId);
}
