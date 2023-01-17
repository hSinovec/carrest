package kingict.carrest.validate;

import kingict.carrest.entity.CarRental;
import kingict.carrest.form.CarRentalEditForm;
import kingict.carrest.form.CarRentalForm;
import kingict.carrest.service.CarRentalService;
import org.apache.commons.validator.GenericValidator;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CarRentalValidator {

    private final CarRentalService carRentalService;

    public CarRentalValidator(CarRentalService carRentalService) {
        this.carRentalService = carRentalService;
    }

    public void validateCreate(CarRentalForm form) {
        List<CarRental> carRentalList = carRentalService.get(form.getCarId(), form.getDateFrom(), form.getDateTo());

        if (!carRentalList.isEmpty()) {
            throw new RuntimeException("Termin vec postoji.");
        }
    }

    public void validateEdit(Long id, CarRentalEditForm form) {
        List<CarRental> carRentalList = carRentalService.get(form.getCarId(), form.getDateFrom(), form.getDateTo())
                .stream()
                .filter(carRental -> !carRental.getId().equals(id))
                .collect(Collectors.toList());
        if(!carRentalList.isEmpty())
            throw new RuntimeException("Postoji vec termin");
    }
}
