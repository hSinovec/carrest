package kingict.carrest.facade;

import kingict.carrest.dto.CarRentalDto;
import kingict.carrest.dto.RentalPersonDto;
import kingict.carrest.dto.TotalPriceDto;
import kingict.carrest.form.CarRentalEditForm;
import kingict.carrest.form.CarRentalForm;
import org.springframework.data.domain.Page;

import java.util.List;


public interface CarRentalFacade {

    void create(CarRentalForm form);

    Page<CarRentalDto> getAll(Integer pageSize, Integer pageNumber, String sort, Boolean descending);

    void delete(Long id);

    void edit(Long carId, CarRentalEditForm form);

    RentalPersonDto getPersonRentals(Long personId);

    public List <TotalPriceDto> getTotalPriceInCarRentalsFromPersonId(Long personId);


}
