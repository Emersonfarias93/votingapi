package br.com.emersonfarias.votingapi.controllers;

import br.com.emersonfarias.votingapi.models.Candidate;
import br.com.emersonfarias.votingapi.services.CandidateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    private final CandidateService candidateService;
    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @GetMapping
    public ResponseEntity<List<Candidate>> findAll() {
        return ResponseEntity.ok(candidateService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Candidate> findById(@PathVariable Long id) {
        return candidateService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Candidate> create(@RequestBody Candidate candidate) {
        return ResponseEntity.status(HttpStatus.CREATED).body(candidateService.save(candidate));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Candidate> update(@PathVariable Long id, @RequestBody Candidate candidate) {
        if (!candidateService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        candidate.setId(id);
        return ResponseEntity.ok(candidateService.save(candidate));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (!candidateService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        candidateService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
