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
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public String generateBulletinUrna(Long idSession) {
        List<Vote> votes = voteRepository.findBySessionId(idSession);

        Map<Candidate, Long> countVotes = votes.stream()
                .collect(Collectors.groupingBy(Vote::getCandidate, Collectors.counting()));
        List<Map.Entry<Candidate, Long>> candidatesOrdered = new ArrayList<>(countVotes.entrySet());
        candidatesOrdered.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));
        long totalVotes = countVotes.values().stream().mapToLong(Long::longValue).sum();
        Candidate winner = candidatesOrdered.get(0).getKey();

        StringBuilder bulletin = new StringBuilder();
        bulletin.append("========================================\n");
        bulletin.append("Data relatório: ").append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))).append("\n");
        bulletin.append("Cargo: ").append(winner.getPosition().getDescription()).append("\n\n");
        bulletin.append("Candidatos        Votos\n");
        for (Map.Entry<Candidate, Long> entry : candidatesOrdered) {
            bulletin.append(String.format("%-15s %d\n", entry.getKey().getName(), entry.getValue()));
        }
        bulletin.append("\nTotal de votos: ").append(totalVotes).append("\n");
        bulletin.append("\nVencedor: ").append(winner.getName()).append("\n");
        bulletin.append("========================================\n");

        return bulletin.toString();
    }
}
