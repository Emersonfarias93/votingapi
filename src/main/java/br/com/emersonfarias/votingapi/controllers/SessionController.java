package br.com.emersonfarias.votingapi.controllers;


import br.com.emersonfarias.votingapi.models.Session;
import br.com.emersonfarias.votingapi.repositories.SessionRepository;
import br.com.emersonfarias.votingapi.services.SessionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/session")
public class SessionController {
    private final SessionService sessionService;
    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping
    public ResponseEntity<List<Session>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(sessionService.findAll());
    }

    @PostMapping("/open")
    public ResponseEntity<Session> openSession() {
        Session session = new Session();
        session.setDateStart(LocalDateTime.now());
        session.setOpen(true);
        return ResponseEntity.status(HttpStatus.CREATED).body(sessionService.save(session));
    }

    @PatchMapping("/{id}/close")
    public ResponseEntity<Session> closeSession(@PathVariable Long id) {
        return sessionService.findById(id).map(session -> {
                     session.setDateEnd(LocalDateTime.now());
                    session.setOpen(false);
                    return ResponseEntity.ok(sessionService.save(session));
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
