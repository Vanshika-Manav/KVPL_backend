package webapp.dto;

import lombok.Data;
import java.util.List;

@Data
public class CourseRequest {
    private Long id;
    private String title;
    private String about;
    private List<String> skills;
    private List<String> programHighlights;
    private List<CourseOutline> courseOutline;
}
