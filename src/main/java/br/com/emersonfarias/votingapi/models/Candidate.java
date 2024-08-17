package br.com.emersonfarias.votingapi.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String party;
    @Column(nullable = false, unique = true)
    private Integer plate;
    @Column(nullable = false)
    private LocalDateTime dateRegister;
    @Column(nullable = false)
    private Boolean snActive;
    @ManyToOne
    @JoinColumn(nullable = false)
    public Position position;

    public Candidate() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public Integer getPlate() {
        return plate;
    }

    public void setPlate(Integer plate) {
        this.plate = plate;
    }

    public LocalDateTime getDateRegister() {
        return dateRegister;
    }

    public void setDateRegister(LocalDateTime dateRegister) {
        this.dateRegister = dateRegister;
    }

    public Boolean getSnActive() {
        return snActive;
    }

    public void setSnActive(Boolean snActive) {
        this.snActive = snActive;
    }
}
