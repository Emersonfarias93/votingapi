package br.com.emersonfarias.votingapi.controllers;

import br.com.emersonfarias.votingapi.models.Position;
import br.com.emersonfarias.votingapi.services.PositionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return positionService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Position> create(@RequestBody Position position) {
        return ResponseEntity.status(HttpStatus.CREATED).body(positionService.save(position));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Position> update(@PathVariable Long id, @RequestBody Position position) {
        if (!positionService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        position.setId(id);
        return ResponseEntity.ok(positionService.save(position));
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
