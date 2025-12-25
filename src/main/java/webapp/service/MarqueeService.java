package webapp.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import webapp.dto.MarqueeRequest;
import webapp.model.Marquee;
import webapp.repository.MarqueeRepository;

import java.util.Optional;

@Service
public class MarqueeService {

    private final MarqueeRepository marqueeRepository;
    private static final Integer PRIMARY_KEY = 1;

    public MarqueeService(MarqueeRepository marqueeRepository) {
        this.marqueeRepository = marqueeRepository;
    }

    @Transactional
    public Marquee saveOrUpdate(MarqueeRequest request) {
        Optional<Marquee> existing = marqueeRepository.findById(PRIMARY_KEY);
        Marquee marquee;
        if (existing.isPresent()) {
            marquee = existing.get();
            marquee.setMarqueeMessage(request.getMarqueeMessage());
        } else {
            marquee = new Marquee();
            marquee.setMarqueeId(PRIMARY_KEY);
            marquee.setMarqueeMessage(request.getMarqueeMessage());
        }
        return marqueeRepository.save(marquee);
    }

    @Transactional(readOnly = true)
    public Optional<Marquee> getMarquee() {
        return marqueeRepository.findById(PRIMARY_KEY);
    }
}
