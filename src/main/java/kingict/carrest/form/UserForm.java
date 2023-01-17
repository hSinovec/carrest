package kingict.carrest.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserForm {

    @NotBlank
    @Size(min=3)
    private String firstname;
    @NotBlank
    @Size(min=3)
    private String lastname;
    @NotBlank
    @Size(min=3)
    private String username;
    @NotBlank
    @Size(min=3)
    private String password;
}
