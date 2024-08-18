package br.com.emersonfarias.votingapi.repositories;

import br.com.emersonfarias.votingapi.models.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    boolean existsByVoterId(Long voterId);
    boolean existsByCandidateId(Long candidateID);
    List<Vote> findBySessionId(Long idSession);
}
