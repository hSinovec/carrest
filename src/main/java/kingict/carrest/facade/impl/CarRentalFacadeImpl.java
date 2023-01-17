package kingict.carrest.facade.impl;

import kingict.carrest.dto.CarRentalDto;
import kingict.carrest.dto.RentalPersonDto;
import kingict.carrest.dto.TotalPriceDto;
import kingict.carrest.entity.CarRental;
import kingict.carrest.facade.CarRentalFacade;
import kingict.carrest.form.CarRentalEditForm;
import kingict.carrest.form.CarRentalForm;
import kingict.carrest.mapper.TotalPriceDtoMapper;
import kingict.carrest.service.CarRentalService;
import kingict.carrest.validate.CarRentalValidator;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class CarRentalFacadeImpl implements CarRentalFacade {

    private static final List<String> VALID_SORTS = Arrays.asList("carId", "personId", "dateFrom", "dateTo");

    private final CarRentalService service;
    private final CarRentalValidator formValidator;
    private final TotalPriceDtoMapper totalPriceDtoMapper;

    public CarRentalFacadeImpl(CarRentalService service, CarRentalValidator formValidator, TotalPriceDtoMapper totalPriceDtoMapper) {
        this.service = service;
        this.formValidator = formValidator;
        this.totalPriceDtoMapper = totalPriceDtoMapper;
    }

    @Override
    @Transactional
    public void create(CarRentalForm form) {
        formValidator.validateCreate(form);

        CarRental carRental = new CarRental();
        BeanUtils.copyProperties(form, carRental);
        service.save(carRental);
    }

    @Override
    public Page<CarRentalDto> getAll(Integer pageSize, Integer pageNumber, String sort, Boolean descending) {
        if(Objects.isNull(pageSize)){
            pageSize = 10;
        }
        if(Objects.isNull(pageNumber)) {
            pageNumber = 0;
        }
        if(!VALID_SORTS.contains(sort)) {
            sort = "personId";
        }
        if(Objects.isNull(descending)){
            descending = false;
        }
        return service.getAll(pageSize, pageNumber, sort, descending)
                .map(carRental -> {
                    CarRentalDto dto = new CarRentalDto();
                    dto.setCarId(carRental.getCarId());
                    dto.setPersonId(carRental.getPersonId());

                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                    dto.setDateFrom(dtf.format(carRental.getDateFrom()));
                    dto.setDateTo(dtf.format(carRental.getDateTo()));

                    return dto;
                });
    }

    @Override
    public void delete(Long id) {
        service.delete(service.get(id));
    }

    @Override
    public void edit(Long id, CarRentalEditForm form) {
        formValidator.validateEdit(id, form);
        CarRental carRental = service.get(id);
        carRental.setDateFrom(form.getDateFrom());
        carRental.setDateTo(form.getDateTo());
        service.save(carRental);
    }

    @Override
    public RentalPersonDto getPersonRentals(Long personId) {
        return service.getPersonRentals(personId);
    }

    @Override
    public List<TotalPriceDto> getTotalPriceInCarRentalsFromPersonId(Long personId) {
        return totalPriceDtoMapper.map(service.getAllRentalsOnPerson(personId));
    }
}