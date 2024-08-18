package br.com.emersonfarias.votingapi.controllers;

import br.com.emersonfarias.votingapi.models.Position;
import br.com.emersonfarias.votingapi.services.PositionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/position")
public class PositionController {
    private final PositionService positionService;
    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }

    @GetMapping
    public ResponseEntity<List<Position>> findAll() {
        return ResponseEntity.ok(positionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Position> findById(@PathVariable Long id) {
        if (positionService.findById(id).isPresent()) {
            return ResponseEntity.ok(positionService.findById(id).get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<List<Position>> create(@RequestBody List<Position> position) {
        return ResponseEntity.status(HttpStatus.CREATED).body(positionService.saveAll(position));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Position> update(@PathVariable Long id, @RequestBody Position position) {

        Optional<Position> existingPositionOpt = positionService.findById(id);

        if (!existingPositionOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Position existingPosition = existingPositionOpt.get();
        existingPosition.setDescription(position.getDescription());
        Position updatedPosition = positionService.save(existingPosition);

        return ResponseEntity.ok(updatedPosition);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {

        if (!positionService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        positionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
