package webapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MarqueeResponse {
    private Integer marqueeId;
    private String marqueeMessage;
}
