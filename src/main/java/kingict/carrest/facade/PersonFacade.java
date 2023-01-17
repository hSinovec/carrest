package kingict.carrest.facade;

import kingict.carrest.dto.PersonDto;
import kingict.carrest.dto.RentalPersonDto;
import kingict.carrest.entity.Person;
import kingict.carrest.form.PersonForm;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PersonFacade {
    void create(PersonForm personForm);

    PersonDto get(Long personId);

    Page<PersonDto> getAll(Integer pageSize, Integer pageNumber, String sort, Boolean descending);

    void delete(Long personId);

    RentalPersonDto getCarRentalCount(Long personId);
}
