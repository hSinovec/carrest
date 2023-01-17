package kingict.carrest.service;

import kingict.carrest.dto.PersonDto;
import kingict.carrest.dto.RentalPersonDto;
import kingict.carrest.entity.Person;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PersonService {
    void save(Person person);


    Person get(Long personId);

    Page<Person> getAll(Integer pageSize, Integer pageNumber, String sort, Boolean descending);

    void delete(Person person);

}
