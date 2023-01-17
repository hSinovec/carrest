package kingict.carrest.repository.impl;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import kingict.carrest.dto.RentalPersonDto;
import kingict.carrest.entity.Car;
import kingict.carrest.entity.CarRental;
import kingict.carrest.repository.CarRentalCustomRepository;
import kingict.carrest.repository.CarRentalRepository;
import kingict.carrest.repository.PersonRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class CarRentalCustomRepositoryImpl implements CarRentalCustomRepository {

    private final EntityManager entityManager;
    private final PersonRepository personRepository;

    public CarRentalCustomRepositoryImpl(EntityManager entityManager,
                                         PersonRepository personRepository) {
        this.entityManager = entityManager;
        this.personRepository = personRepository;
    }

    @Override
    public List<CarRental> findByCarIdAndDateInterval(Long carId, LocalDate dateFrom, LocalDate dateTo) {
        TypedQuery<CarRental> typedQuery = entityManager.createQuery(
                "SELECT cr FROM CarRental cr WHERE cr.carId= :carId AND cr.dateTo >= :dateFrom AND cr.dateFrom <= :dateTo",
                CarRental.class
        );

        typedQuery.setParameter("carId", carId);
        typedQuery.setParameter("dateFrom", dateFrom);
        typedQuery.setParameter("dateTo", dateTo);

        return typedQuery.getResultList();
    }

    @Override
    public RentalPersonDto findPersonRentals(Long personId) {
        TypedQuery<RentalPersonDto> typedQuery = entityManager.createQuery(
                "SELECT new kingict.carrest.dto.RentalPersonDto(p.id, p.firstName, p.lastName, count(p.carRentals)) " +
                        "FROM Person p " +
                        "left join CarRental cr on cr.personId = p.id " +
                        "where p.id = :personId " +
                        "GROUP BY p.id, p.firstName, p.lastName",
                RentalPersonDto.class
        );

        typedQuery.setParameter("personId", personId);
        return typedQuery.getSingleResult();
    }

    @Override
    public List<CarRental> findAllRentalsByPersonId(Long personId) {
        TypedQuery<CarRental> typedQuery = entityManager.createQuery(
                "SELECT cr FROM CarRental cr WHERE cr.personId =: personId",
                CarRental.class
        );

        typedQuery.setParameter("personId", personId);
        return typedQuery.getResultList();
    }
}
