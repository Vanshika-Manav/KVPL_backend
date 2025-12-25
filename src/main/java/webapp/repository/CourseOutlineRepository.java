package webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webapp.model.CourseOutline;

public interface CourseOutlineRepository extends JpaRepository<CourseOutline, Long> {}
