package webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webapp.model.Marquee;

public interface MarqueeRepository extends JpaRepository<Marquee, Integer> {
    // nothing else needed for single-row use-case
}
