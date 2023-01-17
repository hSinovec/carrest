package kingict.carrest.form;

import kingict.carrest.entity.CarRental;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CarRentalEditForm extends CarRental {

    private Long carId;
    private LocalDate dateFrom;
    private LocalDate dateTo;
}
