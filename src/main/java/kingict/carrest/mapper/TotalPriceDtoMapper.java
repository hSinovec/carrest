package kingict.carrest.mapper;

import kingict.carrest.dto.RentalPersonDto;
import kingict.carrest.dto.TotalPriceDto;
import kingict.carrest.entity.CarRental;
import kingict.carrest.entity.Person;

import java.util.List;

public interface TotalPriceDtoMapper {
    public List<TotalPriceDto> map(List <CarRental> carRentals);
}
