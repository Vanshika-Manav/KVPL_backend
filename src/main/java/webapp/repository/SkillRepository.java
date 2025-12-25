package webapp.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import webapp.model.Skill;

public interface SkillRepository extends JpaRepository<Skill, Long> {}
