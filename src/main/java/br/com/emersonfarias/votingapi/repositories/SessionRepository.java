package br.com.emersonfarias.votingapi.repositories;

import br.com.emersonfarias.votingapi.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
}

