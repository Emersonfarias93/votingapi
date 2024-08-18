package br.com.emersonfarias.votingapi.repositories;

import br.com.emersonfarias.votingapi.models.Candidate;
import br.com.emersonfarias.votingapi.models.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {
}
