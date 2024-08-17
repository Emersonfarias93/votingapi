package br.com.emersonfarias.votingapi.repositories;

import br.com.emersonfarias.votingapi.models.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    boolean existsByVoterId(Long voterId);
    boolean existsByCandidateId(Long candidateID);
}
