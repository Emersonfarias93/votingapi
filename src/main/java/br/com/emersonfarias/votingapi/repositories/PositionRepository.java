package br.com.emersonfarias.votingapi.repositories;

import br.com.emersonfarias.votingapi.models.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, Long> {
}
