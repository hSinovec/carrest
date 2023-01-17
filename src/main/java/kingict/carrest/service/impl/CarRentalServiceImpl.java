package kingict.carrest.service.impl;

import kingict.carrest.dto.RentalPersonDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import kingict.carrest.entity.CarRental;
import kingict.carrest.repository.CarRentalRepository;
import kingict.carrest.repository.PersonRepository;
import kingict.carrest.repository.fake.CarRentalFakeRepository;
import kingict.carrest.service.CarRentalService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CarRentalServiceImpl implements CarRentalService {

    private final CarRentalFakeRepository fakeRepository;
    private final CarRentalRepository repository;
    private final PersonRepository personRepository;

    public CarRentalServiceImpl(CarRentalFakeRepository fakeRepository, CarRentalRepository repository,
                                PersonRepository personRepository) {
        this.fakeRepository = fakeRepository;
        this.repository = repository;
        this.personRepository = personRepository;
    }

    @Override
    @Transactional
    public void save(CarRental carRental) {
        repository.save(carRental);
    }

    @Override
    public Page<CarRental> getAll(Integer pageSize, Integer pageNumber, String sort, Boolean descending) {
        Sort sortBy = Sort.by(sort);
        if(Boolean.TRUE.equals(descending)) {
            sortBy = sortBy.descending();
        }
        return repository.findAll(PageRequest.of(pageNumber, pageSize, sortBy));
    }

    @Override
    public List<CarRental> get(Long carId, LocalDate dateFrom, LocalDate dateTo) {
//        return repository.findAllByCarIdAndDateToGreaterThanEqualAndDateFromLessThanEqual(carId, dateFrom, dateTo);
        return repository.findByCarIdAndDateInterval(carId, dateFrom, dateTo);
    }

    @Override
    public List<CarRental> getAllRentalsOnPerson(Long personId) {
        return repository.findAllRentalsByPersonId(personId);
    }

    @Override
    public CarRental get(Long id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Ne postoji car rent s id-em: " + id));
    }

    @Override
    public void delete(CarRental carRental) {
        repository.delete(carRental);
    }

    @Override
    public RentalPersonDto getPersonRentals(Long personId) {
        return repository.findPersonRentals(personId);
    }
}
