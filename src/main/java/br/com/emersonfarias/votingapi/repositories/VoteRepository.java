package br.com.emersonfarias.votingapi.repositories;

import br.com.emersonfarias.votingapi.models.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {
}
