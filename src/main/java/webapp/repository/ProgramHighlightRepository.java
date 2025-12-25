package webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webapp.model.ProgramHighlight;

public interface ProgramHighlightRepository extends JpaRepository<ProgramHighlight, Long> {}
