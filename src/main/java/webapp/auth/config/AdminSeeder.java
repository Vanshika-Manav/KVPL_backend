package webapp.auth.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Value;
import webapp.model.User;
import webapp.repository.UserRepository;

@Component
public class AdminSeeder implements CommandLineRunner {

    private final UserRepository userRepo;
    private final PasswordEncoder encoder;
    private final String adminPassword;

    public AdminSeeder(UserRepository userRepo, PasswordEncoder encoder,
                       @Value("A12sdfxz@") String adminPassword) {
        this.userRepo = userRepo;
        this.encoder = encoder;
        this.adminPassword = adminPassword;
    }

    @Override
    public void run(String... args) {
        userRepo.findByEmail("admin123@gmail.com").orElseGet(() -> {
            User a = new User();
            a.setPassword(encoder.encode(adminPassword));
            a.setEmail("admin123@gmail.com");
            return userRepo.save(a);
        });
    }
}
