package webapp.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DetailsSectionRequest {

    @NotBlank(message = "Address cannot be blank")
    private String address;

    @NotBlank(message = "Email cannot be blank")
    private String email;

    @NotBlank(message = "Phone cannot be blank")
    private String phone;
}
