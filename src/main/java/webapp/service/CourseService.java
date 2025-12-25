package webapp.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import webapp.dto.CourseDTO;
import webapp.dto.CourseRequest;
import webapp.model.Course;
import webapp.model.CourseOutline;
import webapp.model.ProgramHighlight;
import webapp.model.Skill;
import webapp.repository.CourseRepository;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

//    @Transactional
//    public Course saveCourse(Course course) {
//        // Set course reference in child lists
//        if (course.getSkills() != null)
//            course.getSkills().forEach(skill -> skill.setCourse(course));
//        if (course.getProgramHighlights() != null)
//            course.getProgramHighlights().forEach(highlight -> highlight.setCourse(course));
//        if (course.getCourseOutline() != null)
//            course.getCourseOutline().forEach(outline -> outline.setCourse(course));
//
//        return courseRepository.save(course);
//    }

    @Transactional
    public Course saveCourse(CourseRequest req) {
        Course course = new Course();
        if (req.getId() != null) {
            course.setId(req.getId());
        }
        course.setTitle(req.getTitle());
        course.setAbout(req.getAbout());

        // Skills
        List<Skill> skills = req.getSkills().stream().map(s -> {
            Skill skill = new Skill();
            skill.setName(s);
            skill.setCourse(course);
            return skill;
        }).collect(Collectors.toList());
        course.setSkills(skills);

        // Highlights
        List<ProgramHighlight> highlights = req.getProgramHighlights().stream().map(h -> {
            ProgramHighlight ph = new ProgramHighlight();
            ph.setDescription(h);
            ph.setCourse(course);
            return ph;
        }).collect(Collectors.toList());
        course.setProgramHighlights(highlights);

        // Outline
        List<CourseOutline> outlines = req.getCourseOutline().stream().map(o -> {
            CourseOutline co = new CourseOutline();
            co.setTitle(o.getTitle());
            co.setDescription(o.getDescription());
            co.setCourse(course);
            return co;
        }).collect(Collectors.toList());
        course.setCourseOutline(outlines);

        return courseRepository.save(course);
    }

    public List<Course> getAllCourses() {
    return courseRepository.findAll();
    }

    public Course getCourseById(Long id) {
        return courseRepository.findById(id).orElse(null);
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    public Course updateCourse(Long id, CourseDTO dto) {
        Course existingCourse = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id " + id));

        existingCourse.setTitle(dto.getTitle());
        existingCourse.setAbout(dto.getAbout());

        // Replace Skills
        existingCourse.getSkills().clear();
        if (dto.getSkills() != null) {
            dto.getSkills().forEach(skillName -> {
                Skill skill = new Skill();
                skill.setName(skillName);
                skill.setCourse(existingCourse);
                existingCourse.getSkills().add(skill);
            });
        }

        // Replace Program Highlights
        existingCourse.getProgramHighlights().clear();
        if (dto.getProgramHighlights() != null) {
            dto.getProgramHighlights().forEach(highlightText -> {
                ProgramHighlight highlight = new ProgramHighlight();
                highlight.setDescription(highlightText);
                highlight.setCourse(existingCourse);
                existingCourse.getProgramHighlights().add(highlight);
            });
        }

        // Replace Course Outline
        existingCourse.getCourseOutline().clear();
        if (dto.getCourseOutline() != null) {
            dto.getCourseOutline().forEach(outlineDTO -> {
                CourseOutline outline = new CourseOutline();
                outline.setTitle(outlineDTO.getTitle());
                outline.setDescription(outlineDTO.getDescription());
                outline.setCourse(existingCourse);
                existingCourse.getCourseOutline().add(outline);
            });
        }

        return courseRepository.save(existingCourse);
    }




}