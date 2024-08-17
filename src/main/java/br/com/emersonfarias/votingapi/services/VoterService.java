package br.com.emersonfarias.votingapi.services;

import br.com.emersonfarias.votingapi.models.Voter;
import br.com.emersonfarias.votingapi.repositories.VoterRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoterService {
    private final VoterRepository voterRepository;
    public VoterService(VoterRepository voterRepository) {
        this.voterRepository = voterRepository;
    }
    public Voter findById(Long id) {
        return voterRepository.findById(id).orElse(null);
    }
    public List<Voter> findAll() {
        return voterRepository.findAll();
    }
    public Voter save(Voter voter) {
        return voterRepository.save(voter);
    }
    public Voter update(Voter voter) {
        return voterRepository.save(voter);
    }
    public void delete(Long id) {
        voterRepository.deleteById(id);
    }
}
