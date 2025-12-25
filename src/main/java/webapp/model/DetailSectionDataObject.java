package webapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailSectionDataObject {
    private Integer id;
    private String address;
    private String email;
    private String phone;
}
