package com.bigcorp.stock.cours;

import org.springframework.beans.factory.annotation.Autowired;

public class MaPremiereClasse {
    private Integer id;
    private String name;

    @Autowired
    private MaDeuxiemeClasse maDeuxiemeClasse;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void sayCoucou() {
        System.out.println("coucou de " + name);
    }

    public MaDeuxiemeClasse getMaDeuxiemeClasse() {
        return maDeuxiemeClasse;
    }

    public void setMaDeuxiemeClasse(MaDeuxiemeClasse maDeuxiemeClasse) {
        this.maDeuxiemeClasse = maDeuxiemeClasse;
    }
}
