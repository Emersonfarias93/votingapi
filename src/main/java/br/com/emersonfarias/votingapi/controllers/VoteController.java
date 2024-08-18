package br.com.emersonfarias.votingapi.controllers;

import br.com.emersonfarias.votingapi.services.VoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/votes")
public class VoteController {
    private final VoteService voteService;
    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @PostMapping
    public ResponseEntity<Void> registerVotes(@RequestParam Long candidateId,
                                              @RequestParam Long voterId,
                                              @RequestParam Long sessionId) {
        voteService.registerVote(candidateId, voterId, sessionId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
