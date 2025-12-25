package webapp.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import webapp.dto.DetailsSectionRequest;
import webapp.model.DetailsSection;
import webapp.repository.DetailsSectionRepository;

import java.util.Optional;

@Service
public class DetailsSectionService {

    private final DetailsSectionRepository repository;
    private static final Integer PRIMARY_KEY = 1;

    public DetailsSectionService(DetailsSectionRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public DetailsSection saveOrUpdate(DetailsSectionRequest request) {
        Optional<DetailsSection> existing = repository.findById(PRIMARY_KEY);
        DetailsSection details;

        if (existing.isPresent()) {
            details = existing.get();
            details.setAddress(request.getAddress());
            details.setEmail(request.getEmail());
            details.setPhone(request.getPhone());
        } else {
            details = new DetailsSection();
            details.setId(PRIMARY_KEY);
            details.setAddress(request.getAddress());
            details.setEmail(request.getEmail());
            details.setPhone(request.getPhone());
        }

        return repository.save(details);
    }

    @Transactional(readOnly = true)
    public Optional<DetailsSection> getDetails() {
        return repository.findById(PRIMARY_KEY);
    }
}
