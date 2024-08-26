package com.bigcorp.stock.cours;

public class MaDeuxiemeClasse {
    private Integer id;
    private String city;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void sayHello() {
        System.out.println("Hello de " + city);
    }
}
