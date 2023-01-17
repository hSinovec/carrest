package kingict.carrest.facade;

import kingict.carrest.dto.CarDto;
import kingict.carrest.form.CarForm;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CarFacade {

    void create(CarForm carForm);

    CarDto get(Long carId);

    Page<CarDto> getAll(Integer pageSize, Integer pageNumber, String sort, Boolean descending);

    void delete(Long carId);

}
