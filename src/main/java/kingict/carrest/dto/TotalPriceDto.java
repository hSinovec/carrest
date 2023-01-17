package kingict.carrest.dto;

import lombok.Data;

@Data
public class TotalPriceDto {
    private Long personId;
    private Long carId;
    private Long totalDays;
    private Long totalPrice;
}
