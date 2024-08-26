package com.bigcorp.stock.cours;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigClasses {

    @Bean
    public MaPremiereClasse genereMaPremiereClasse() {
        System.out.println("methode genereMaPremiereClasse");
        return new MaPremiereClasse();
    }

    @Bean
    public MaDeuxiemeClasse genereMaDeuxiemeClasse() {
        System.out.println("methode genereMaDeuxiemeClasse");
        return new MaDeuxiemeClasse();
    }
}
