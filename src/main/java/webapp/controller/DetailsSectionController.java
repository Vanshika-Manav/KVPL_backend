package webapp.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webapp.dto.DetailsSectionRequest;
import webapp.dto.DetailsSectionResponse;
import webapp.model.DetailsSection;
import webapp.service.DetailsSectionService;

@RestController
@RequestMapping("/api/details-section")
public class DetailsSectionController {

    private final DetailsSectionService service;

    public DetailsSectionController(DetailsSectionService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<DetailsSectionResponse> saveOrUpdate(
            @Valid @RequestBody DetailsSectionRequest request) {

        DetailsSection saved = service.saveOrUpdate(request);

        DetailsSectionResponse response =
                new DetailsSectionResponse(
                        saved.getId(),
                        saved.getAddress(),
                        saved.getEmail(),
                        saved.getPhone()
                );

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<DetailsSectionResponse> get() {
        return service.getDetails()
                .map(d -> ResponseEntity.ok(
                        new DetailsSectionResponse(
                                d.getId(),
                                d.getAddress(),
                                d.getEmail(),
                                d.getPhone()
                        )))
                .orElseGet(() -> ResponseEntity.noContent().build());
    }
}
