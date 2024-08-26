package com.bigcorp.stock.cours.model;

import jakarta.persistence.*;

@Entity
public class Premium {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    private boolean premium;
    private Integer level;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isPremium() {
        return premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
