package kingict.carrest.repository;

import kingict.carrest.entity.CarRental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CarRentalRepository extends JpaRepository<CarRental, Long>, CarRentalCustomRepository {
    List<CarRental> findAllByCarIdAndDateToGreaterThanEqualAndDateFromLessThanEqual(Long carId, LocalDate dateFrom, LocalDate dateTo);
}
