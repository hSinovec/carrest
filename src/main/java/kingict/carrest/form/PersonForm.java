package kingict.carrest.form;
import lombok.Data;

import java.time.LocalDate;


@Data
public class PersonForm {

    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private Character gender;
}
