package br.com.emersonfarias.votingapi.controllers;


import br.com.emersonfarias.votingapi.services.VoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/urna-bulletin")
public class UrnaBulletinController {
    private final VoteService voteService;
    public UrnaBulletinController(VoteService voteService) {
        this.voteService = voteService;
    }

    @GetMapping("/{idSessao}")
    public ResponseEntity<String> generateBulletinUrna(@RequestParam Long idSession) {
        String bulletin = voteService.generateBulletinUrna(idSession);
        return ResponseEntity.ok(bulletin);
    }
}
