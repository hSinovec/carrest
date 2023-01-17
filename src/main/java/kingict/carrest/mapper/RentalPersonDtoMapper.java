package kingict.carrest.mapper;

import kingict.carrest.dto.RentalPersonDto;
import kingict.carrest.entity.Person;

public interface RentalPersonDtoMapper {
    RentalPersonDto map(Person person);
}
