package kingict.carrest.facade.impl;

import kingict.carrest.dto.PersonDto;
import kingict.carrest.dto.RentalPersonDto;
import kingict.carrest.entity.Person;
import kingict.carrest.facade.PersonFacade;
import kingict.carrest.form.PersonForm;
import kingict.carrest.mapper.RentalPersonDtoMapper;
import kingict.carrest.repository.PersonRepository;
import kingict.carrest.service.PersonService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PersonFacadeImpl implements PersonFacade {

    private static final List<String> VALID_SORTS = Arrays.asList("firstName", "gender");

    private final PersonService service;
    private final RentalPersonDtoMapper rentalPersonDtoMapper;
    private final PersonRepository personRepository;

    public PersonFacadeImpl(PersonService service, RentalPersonDtoMapper rentalPersonDtoMapper,
                            PersonRepository personRepository) {
        this.service = service;
        this.rentalPersonDtoMapper = rentalPersonDtoMapper;
        this.personRepository = personRepository;
    }

    @Override
    public void create(PersonForm personForm) {
        Person person = new Person();
        person.setFirstName(personForm.getFirstName());
        person.setLastName(personForm.getLastName());
        person.setBirthday(personForm.getBirthday());
        person.setGender(personForm.getGender());
//        BeanUtils.copyProperties(personForm, person);
        service.save(person);
    }

    @Override
    public PersonDto get(Long personId) {
        return Optional.of(service.get(personId))
                .map(person -> {
                    PersonDto dto = new PersonDto();
                    dto.setName(person.getFirstName() + " " + person.getLastName());
                    return dto;
                }).orElse(null);
    }

    @Override
    public Page<PersonDto> getAll(Integer pageSize, Integer pageNumber, String sort, Boolean descending) {
        if(Objects.isNull(pageSize)){
            pageSize = 10;
        }
        if(Objects.isNull(pageNumber)) {
            pageNumber = 0;
        }
        if(!VALID_SORTS.contains(sort)) {
            sort = "firstName";
        }
        if(Objects.isNull(descending)){
            descending = false;
        }

        return service.getAll(pageSize, pageNumber, sort, descending)
                .map(person -> {
                    PersonDto dto = new PersonDto();
                    dto.setName(person.getFirstName() + " " + person.getLastName());
                    return dto;
                });
    }

    @Override
    public void delete(Long personId) {
        service.delete(service.get(personId));
    }

    @Override
    public RentalPersonDto getCarRentalCount(Long personId) {
        return rentalPersonDtoMapper.map(service.get(personId));
//                .map(p -> {
//                    RentalPersonDto dto = new RentalPersonDto();
//                    dto.setFirstName(p.getFirstName());
//                    dto.setLastname(p.getLastName());
//                    dto.setPersonId(p.getId());
//                    dto.setRentalCount((long) p.getCarRentals().size());
//                    return dto;
//                }).orElse(null);
    }
}

