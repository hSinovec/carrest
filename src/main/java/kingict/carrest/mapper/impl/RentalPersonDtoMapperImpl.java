package kingict.carrest.mapper.impl;

import kingict.carrest.dto.RentalPersonDto;
import kingict.carrest.entity.Person;
import kingict.carrest.mapper.RentalPersonDtoMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class RentalPersonDtoMapperImpl implements RentalPersonDtoMapper {

    @Override
    public RentalPersonDto map(Person person) {
        if (Objects.isNull(person)) {
            return null;
        }

        RentalPersonDto dto = new RentalPersonDto();
        dto.setFirstName(person.getFirstName());
        dto.setLastname(person.getLastName());
        dto.setPersonId(person.getId());
        dto.setRentalCount((long) person.getCarRentals().size());
        return dto;

    }
}
