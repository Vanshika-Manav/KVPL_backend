package webapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CourseDTO {
    public String title;
    public String about;
    public List<String> skills;
    public List<String> programHighlights;
    public List<CourseOutline> courseOutline;
}
