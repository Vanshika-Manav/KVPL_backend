package webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webapp.model.User;

import java.util.*;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}

