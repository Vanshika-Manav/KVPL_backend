package webapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webapp.model.Enquiry;
import webapp.service.EnquiryService;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")   // Optional, allows frontend access
public class EnquiryController {

    private final EnquiryService enquiryService;

    public EnquiryController(EnquiryService enquiryService) {
        this.enquiryService = enquiryService;
    }

    // POST: Save enquiry
    @PostMapping("/save")
    public ResponseEntity<Enquiry> saveEnquiry(@RequestBody Enquiry enquiry) {
        Enquiry saved = enquiryService.save(enquiry);
        return ResponseEntity.ok(saved);
    }

    // GET: Get all enquiries
    @GetMapping("/all")
    public ResponseEntity<List<Enquiry>> getAllEnquiries() {
        return ResponseEntity.ok(enquiryService.getAll());
    }

    // DELETE: Delete enquiry by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnquiry(@PathVariable Long id) {
        enquiryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
