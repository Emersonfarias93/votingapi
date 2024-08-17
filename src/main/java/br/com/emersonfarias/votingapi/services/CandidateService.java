package br.com.emersonfarias.votingapi.services;

import br.com.emersonfarias.votingapi.models.Candidate;
import br.com.emersonfarias.votingapi.models.Voter;
import br.com.emersonfarias.votingapi.repositories.CandidateRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CandidateService {
    private final CandidateRepository candidateRepository;
    public CandidateService(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public List<Candidate> findAll() {
        return candidateRepository.findAll();
    }

    public Optional<Candidate> findById(Long id) {
        return candidateRepository.findById(id);
    }

    public Candidate save(Candidate candidate) {
        return candidateRepository.save(candidate);
    }

    public void deleteById(Long id) {
        candidateRepository.deleteById(id);
    }

    public Candidate update(Candidate candidate) {
        return candidateRepository.save(candidate);
    }
}
