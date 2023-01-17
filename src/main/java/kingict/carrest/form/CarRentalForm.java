package kingict.carrest.form;



import lombok.Data;
import javax.validation.constraints.NotNull;


@Data
public class CarRentalForm extends CarRentalEditForm {

    @NotNull(message = "Person ID ne smije biti null")
    private Long personId;
}
