package com.bigcorp.stock.cours.model;

import jakarta.persistence.*;

@Entity
public class NewManager {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @ManyToOne //plusieurs managers sur une equipe mais un manager a une equipe
    @JoinColumn(name = "TEAM_ID")
    private NewTeam team;

    private String nom;
    private String prenom;
    private int poids;
    private int salaire;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getPoids() {
        return poids;
    }

    public void setPoids(int poids) {
        this.poids = poids;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getSalaire() {
        return salaire;
    }

    public void setSalaire(int salaire) {
        this.salaire = salaire;
    }

    public NewTeam getTeam() {
        return team;
    }

    public void setTeam(NewTeam team) {
        this.team = team;
    }
}
