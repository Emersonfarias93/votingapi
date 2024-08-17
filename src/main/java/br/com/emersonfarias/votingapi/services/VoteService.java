package br.com.emersonfarias.votingapi.services;

import br.com.emersonfarias.votingapi.models.Candidate;
import br.com.emersonfarias.votingapi.models.Session;
import br.com.emersonfarias.votingapi.models.Vote;
import br.com.emersonfarias.votingapi.models.Voter;
import br.com.emersonfarias.votingapi.repositories.CandidateRepository;
import br.com.emersonfarias.votingapi.repositories.SessionRepository;
import br.com.emersonfarias.votingapi.repositories.VoteRepository;
import br.com.emersonfarias.votingapi.repositories.VoterRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class VoteService {
    private final VoteRepository voteRepository;
    private final CandidateRepository candidateRepository;
    private final VoterRepository voterRepository;
    private final SessionRepository sessionRepository;

    public VoteService(VoteRepository voteRepository,
                       CandidateRepository candidateRepository,
                       VoterRepository voterRepository,
                       SessionRepository sessionRepository) {
        this.voteRepository = voteRepository;
        this.candidateRepository = candidateRepository;
        this.voterRepository = voterRepository;
        this.sessionRepository = sessionRepository;
    }

    public void registerVote(Long candidateId, Long voterId, Long sessionId) {
        if (voteRepository.existsByVoterId(voterId)) {
            throw new IllegalArgumentException("Eleitor já votou.");
        }

        Optional<Candidate> candidateOpt = candidateRepository.findById(candidateId);
        Optional<Voter> voterOpt = voterRepository.findById(voterId);
        Optional<Session> sessionOpt = sessionRepository.findById(sessionId);

        if (candidateOpt.isEmpty() || voterOpt.isEmpty() || sessionOpt.isEmpty()) {
            throw new IllegalArgumentException("Candidato, Eleitor ou Sessao inválidos.");
        }

        Session session = sessionOpt.get();
        if (!session.getOpen()) {
            throw new IllegalStateException("Sessão não está aberta para votação.");
        }

        Vote vote = new Vote();
        vote.setCandidate(candidateOpt.get());
        vote.setVoter(voterOpt.get());
        vote.setSession(session);
        vote.setDateRegister(LocalDateTime.now());

        voteRepository.save(vote);
    }

}
