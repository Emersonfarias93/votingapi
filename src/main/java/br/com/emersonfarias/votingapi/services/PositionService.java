package br.com.emersonfarias.votingapi.services;

import br.com.emersonfarias.votingapi.models.Position;
import br.com.emersonfarias.votingapi.models.Position;
import br.com.emersonfarias.votingapi.repositories.PositionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PositionService {
    private final PositionRepository positionRepository;
    public PositionService(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    public List<Position> findAll() {
        return positionRepository.findAll();
    }

    public Optional<Position> findById(Long id) {
        return positionRepository.findById(id);
    }

    public Position save(Position Position) {
        return positionRepository.save(Position);
    }

    public List<Position> saveAll(List<Position> Position) {
        return positionRepository.saveAll(Position);
    }

    public void deleteById(Long id) {
        positionRepository.deleteById(id);
    }
}
