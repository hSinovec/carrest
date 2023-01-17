package kingict.carrest.service.impl;

import kingict.carrest.dto.RentalPersonDto;
import kingict.carrest.entity.Person;
import kingict.carrest.repository.PersonRepository;
import kingict.carrest.repository.fake.PersonFakeRepository;
import kingict.carrest.service.PersonService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonFakeRepository personFakeRepository;
    private final PersonRepository repository;

    public PersonServiceImpl(PersonFakeRepository personFakeRepository, PersonRepository repository) {
        this.personFakeRepository = personFakeRepository;
        this.repository = repository;
    }

    @Override
    public void save(Person person) {
        repository.save(person);
    }

    @Override
    public Person get(Long personId) {
        return repository.findById(personId)
                .orElseThrow(() -> new IllegalArgumentException("Nema osobe s tim id-em: " + personId));
    }

    @Override
    public Page<Person> getAll(Integer pageSize, Integer pageNumber, String sort, Boolean descending) {
        Sort sortBy = Sort.by(sort);
        if(Boolean.TRUE.equals(descending)) {
            sortBy = sortBy.descending();
        }
         return repository.findAll(PageRequest.of(pageNumber, pageSize, sortBy));
//        return personPage;
    }

    @Override
    public void delete(Person person) {
        repository.delete(person);
    }


}
