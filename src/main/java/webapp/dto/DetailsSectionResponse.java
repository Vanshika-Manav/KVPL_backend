package webapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DetailsSectionResponse {
    private Integer id;
    private String address;
    private String email;
    private String phone;
}
