package kingict.carrest.dto;

import lombok.Data;

@Data
public class RentalPersonDto {
    private Long personId;
    private String firstName;
    private String lastname;
    private Long rentalCount;

}
