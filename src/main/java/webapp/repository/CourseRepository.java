package webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webapp.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

}

