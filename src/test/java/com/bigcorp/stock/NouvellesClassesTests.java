package com.bigcorp.stock;

import com.bigcorp.stock.cours.MaDeuxiemeClasse;
import com.bigcorp.stock.cours.MaPremiereClasse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class NouvellesClassesTests {

    @Autowired
    MaPremiereClasse maPremiereClasse;

    @Autowired
    MaDeuxiemeClasse maDeuxiemeClasse;

    @Test
    public void testMaPremiereClasse() {
        Assertions.assertNotNull(maPremiereClasse);
        maPremiereClasse.setName("Picsou");
        maPremiereClasse.sayCoucou();
        maPremiereClasse.getMaDeuxiemeClasse().setCity("Donaldville");
        maPremiereClasse.getMaDeuxiemeClasse().sayHello();
    }

    @Test
    public void testMaDeuxiemeClasse() {
        Assertions.assertNotNull(maDeuxiemeClasse);
        maDeuxiemeClasse.setCity("Blois");
        maDeuxiemeClasse.sayHello();
    }
}
