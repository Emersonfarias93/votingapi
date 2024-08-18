package br.com.emersonfarias.votingapi.controllers;

import br.com.emersonfarias.votingapi.models.Candidate;
import br.com.emersonfarias.votingapi.models.Position;
import br.com.emersonfarias.votingapi.models.dtos.CandidateDTO;
import br.com.emersonfarias.votingapi.services.CandidateService;
import br.com.emersonfarias.votingapi.services.PositionService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    private final CandidateService candidateService;
    private final PositionService positionService;

    public CandidateController(CandidateService candidateService, PositionService positionService) {
        this.candidateService = candidateService;
        this.positionService = positionService;
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
    public ResponseEntity<Candidate> create(@RequestBody CandidateDTO candidateDTO) throws ChangeSetPersister.NotFoundException {
        Position position = positionService.findById(candidateDTO.getPositionId())
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());

        Candidate candidate = new Candidate();
        candidate.setName(candidateDTO.getName());
        candidate.setParty(candidateDTO.getParty());
        candidate.setPlate(candidateDTO.getPlate());
        candidate.setDateRegister(candidateDTO.getDateRegister());
        candidate.setSnActive(candidateDTO.getSnActive());
        candidate.setPosition(position);

        Candidate savedCandidate = candidateService.save(candidate);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedCandidate);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Candidate> update(@PathVariable Long id, @RequestBody CandidateDTO candidateDTO) {
        Optional<Candidate> candidateOpt = candidateService.findById(id);
        if (!candidateOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Candidate candidate = candidateOpt.get();
        Optional.ofNullable(candidateDTO.getName()).ifPresent(candidate::setName);
        Optional.ofNullable(candidateDTO.getParty()).ifPresent(candidate::setParty);
        Optional.ofNullable(candidateDTO.getPlate()).ifPresent(candidate::setPlate);
        Optional.ofNullable(candidateDTO.getSnActive()).ifPresent(candidate::setSnActive);

        Optional.ofNullable(candidateDTO.getPositionId()).ifPresent(positionId -> {
            Position position = positionService.findById(positionId).orElse(null);
            candidate.setPosition(position);
        });
        Candidate updatedCandidate = candidateService.update(candidate);
        return ResponseEntity.status(HttpStatus.OK).body(updatedCandidate);
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
