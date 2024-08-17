package br.com.emersonfarias.votingapi.controllers;


import br.com.emersonfarias.votingapi.models.Session;
import br.com.emersonfarias.votingapi.repositories.SessionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/session")
public class SessionController {
    private final SessionRepository sessionRepository;
    public SessionController(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @PostMapping("/open")
    public ResponseEntity<Session> abrirSessao() {
        Session session = new Session();
        session.setDateStart(LocalDateTime.now());
        session.setOpen(true);
        return ResponseEntity.status(HttpStatus.CREATED).body(sessionRepository.save(session));
    }

    @PatchMapping("/{id}/close")
    public ResponseEntity<Session> fecharSessao(@PathVariable Long id) {
        return sessionRepository.findById(id)
                .map(sessao -> {
                    sessao.setDateEnd(LocalDateTime.now());
                    sessao.setOpen(false);
                    return ResponseEntity.ok(sessionRepository.save(sessao));
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
