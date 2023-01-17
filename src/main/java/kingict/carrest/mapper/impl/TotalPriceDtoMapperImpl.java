package kingict.carrest.mapper.impl;

import kingict.carrest.dto.RentalPersonDto;
import kingict.carrest.dto.TotalPriceDto;
import kingict.carrest.entity.CarRental;
import kingict.carrest.mapper.TotalPriceDtoMapper;
import org.springframework.http.server.DelegatingServerHttpResponse;
import org.springframework.stereotype.Component;

import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class TotalPriceDtoMapperImpl implements TotalPriceDtoMapper {

    @Override
    public List <TotalPriceDto> map(List <CarRental> carRentals) {
        List <TotalPriceDto> totalPriceDtos = new ArrayList<>();
        for (CarRental carRental : carRentals) {
            if (Objects.isNull(carRental)) {
                return null;
            }
            TotalPriceDto dto = new TotalPriceDto();
            dto.setPersonId(carRental.getPersonId());
            dto.setCarId(carRental.getCarId());

            Long totalDays = carRental.getDateFrom().until(carRental.getDateTo(), ChronoUnit.DAYS);
            Long totalPrice = totalDays * carRental.getCar().getPrice();

            dto.setTotalDays(totalDays);
            dto.setTotalPrice(totalPrice);
            totalPriceDtos.add(dto);
        }
        return totalPriceDtos;
    }
}
