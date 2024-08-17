package br.com.emersonfarias.votingapi.repositories;

import br.com.emersonfarias.votingapi.models.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
}
