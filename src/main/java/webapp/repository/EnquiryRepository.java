package webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webapp.model.Enquiry;

public interface EnquiryRepository extends JpaRepository<Enquiry, Long> {
}
