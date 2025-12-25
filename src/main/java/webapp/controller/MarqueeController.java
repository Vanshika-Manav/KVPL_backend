package webapp.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webapp.dto.MarqueeRequest;
import webapp.dto.MarqueeResponse;
import webapp.model.Marquee;
import webapp.service.MarqueeService;

@RestController
@RequestMapping("/api/marquee")
public class MarqueeController {

    private final MarqueeService marqueeService;

    public MarqueeController(MarqueeService marqueeService) {
        this.marqueeService = marqueeService;
    }

    @PostMapping
    public ResponseEntity<MarqueeResponse> saveOrUpdate(@Valid @RequestBody MarqueeRequest request) {
        Marquee saved = marqueeService.saveOrUpdate(request);
        MarqueeResponse response = new MarqueeResponse(saved.getMarqueeId(), saved.getMarqueeMessage());
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<MarqueeResponse> get() {
        return marqueeService.getMarquee()
                .map(m -> ResponseEntity.ok(new MarqueeResponse(m.getMarqueeId(), m.getMarqueeMessage())))
                .orElseGet(() -> ResponseEntity.noContent().build()); // 204 if no marquee yet
    }
}
