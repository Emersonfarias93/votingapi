package br.com.emersonfarias.votingapi.repositories;

import br.com.emersonfarias.votingapi.models.Voter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoterRepository extends JpaRepository<Voter, Long> {
    Optional<Voter> findByCpf(String cpf);
}
