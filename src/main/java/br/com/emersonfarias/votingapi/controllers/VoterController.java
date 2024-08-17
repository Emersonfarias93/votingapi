package br.com.emersonfarias.votingapi.controllers;

import br.com.emersonfarias.votingapi.models.Voter;
import br.com.emersonfarias.votingapi.services.VoterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/voters")
public class VoterController {
    private final VoterService voterService;
    public VoterController(VoterService voterService) {
        this.voterService = voterService;
    }

    @GetMapping
    public ResponseEntity<List<Voter>> findAll() {
        return ResponseEntity.ok(voterService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        return voterService.findById(id)
                .map(ResponseEntity.ok())
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Voter> create(@RequestBody Voter voter) {
        return ResponseEntity.status(HttpStatus.CREATED).body(voterService.save(voter));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Voter> update(@PathVariable Long id, @RequestBody Voter voter) {
        if (voterService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        voter.setId(id);
        return ResponseEntity.ok(voterService.save(voter));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (voterService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        voterService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
