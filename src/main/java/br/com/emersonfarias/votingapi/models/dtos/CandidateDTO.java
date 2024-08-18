package br.com.emersonfarias.votingapi.models.dtos;

import java.time.LocalDateTime;

public class CandidateDTO {
    private String name;
    private String party;
    private Integer plate;
    private LocalDateTime dateRegister;
    private Boolean snActive;
    private Long positionId;

    public CandidateDTO() {
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

    public Long getPositionId() {
        return positionId;
    }

    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }
}
