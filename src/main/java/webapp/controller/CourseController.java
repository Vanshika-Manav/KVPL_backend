package webapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webapp.dto.CourseDTO;
import webapp.dto.CourseRequest;
import webapp.model.Course;
import webapp.service.CourseService;

@RestController
@RequestMapping("/api/courses")
@CrossOrigin(origins = "*") // allow all origins for frontend
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody CourseRequest request) {
        Course saved = courseService.saveCourse(request);
        return ResponseEntity.ok(saved);
    }

    // GET: Fetch all courses
    @GetMapping
    public ResponseEntity<?> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

        @GetMapping("/{id}")
    public ResponseEntity<?> getCourseById(@PathVariable Long id) {
        Course course = courseService.getCourseById(id);
        if (course == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(course);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody CourseDTO course) {
        Course updated = courseService.updateCourse(id, course);
        return ResponseEntity.ok(updated);
    }


}