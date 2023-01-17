package kingict.carrest.repository;

import kingict.carrest.dto.RentalPersonDto;
import kingict.carrest.entity.CarRental;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CarRentalCustomRepository {

    List<CarRental> findByCarIdAndDateInterval(Long carId, LocalDate dateFrom, LocalDate dateTo);

    RentalPersonDto findPersonRentals(Long personId);

    public List<CarRental> findAllRentalsByPersonId(Long personId);

}
