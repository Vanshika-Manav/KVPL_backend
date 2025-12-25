package webapp.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String password; // BCrypt encoded

    @Column(nullable = false, unique = true)
    private String email;  // Email (username ke alawa bhi unique hoga)
}
