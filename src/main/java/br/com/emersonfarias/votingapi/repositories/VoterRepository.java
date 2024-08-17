package br.com.emersonfarias.votingapi.repositories;

import br.com.emersonfarias.votingapi.models.Voter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoterRepository extends JpaRepository<Voter, Long> {
}
