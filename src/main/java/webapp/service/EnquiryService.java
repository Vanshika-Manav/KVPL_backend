package webapp.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import webapp.model.Enquiry;
import webapp.repository.EnquiryRepository;

import java.util.List;

@Service
public class EnquiryService {

    private final EnquiryRepository enquiryRepository;

    public EnquiryService(EnquiryRepository enquiryRepository) {
        this.enquiryRepository = enquiryRepository;
    }

    // Save enquiry
    @Transactional
    public Enquiry save(Enquiry enquiry) {
        return enquiryRepository.save(enquiry);
    }

    // Get all enquiries
    @Transactional(readOnly = true)
    public List<Enquiry> getAll() {
        return enquiryRepository.findAll();
    }

    // Delete enquiry by ID
    @Transactional
    public void delete(Long id) {
        enquiryRepository.deleteById(id);
    }
}
