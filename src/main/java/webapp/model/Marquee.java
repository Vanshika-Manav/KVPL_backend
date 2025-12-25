package webapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "MARQUEE")
public class Marquee {
    @Id
    private Integer marqueeId;

    private String marqueeMessage;
}
