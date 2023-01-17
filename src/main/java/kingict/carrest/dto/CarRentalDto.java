package kingict.carrest.dto;

import lombok.Data;

@Data
public class CarRentalDto {

    private Long personId;
    private Long carId;
    private String dateFrom;
    private String dateTo;
}
