package webapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class DetailsSection {
    @Id
    private Integer id;
    private String address;
    private String email;
    private String phone;
}
