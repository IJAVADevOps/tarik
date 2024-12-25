package tariq.mini.projet.dto;

import java.util.List;

public class FiliereDTO {
    private Long id;
    private String name;
    private List<Long> etudiants; // Liste des IDs des étudiants

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Long> getEtudiants() {
        return etudiants;
    }

    public void setEtudiants(List<Long> etudiants) {
        this.etudiants = etudiants;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}