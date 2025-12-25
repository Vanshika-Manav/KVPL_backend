package webapp.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MarqueeRequest {
    @NotBlank(message = "marqueeMessage cannot be blank")
    private String marqueeMessage;
}
