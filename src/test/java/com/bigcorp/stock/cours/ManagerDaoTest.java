package com.bigcorp.stock.cours;

import com.bigcorp.stock.cours.dao.ManagerNewDao;
import com.bigcorp.stock.cours.dao.TeamNewDao;
import com.bigcorp.stock.cours.model.NewManager;
import com.bigcorp.stock.cours.model.NewTeam;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ManagerDaoTest {
    @Autowired
    ManagerNewDao managerDao;
    @Autowired
    TeamNewDao teamDao;

    @Test
    public void saveManager() {
        NewManager newManager = new NewManager();
        newManager.setNom("Roger");

        NewManager savedManager = managerDao.save(newManager);

        Assertions.assertNotNull(savedManager.getId());
        Assertions.assertEquals("Roger", savedManager.getNom());

        NewManager managerFromDataBase = managerDao.findById(savedManager.getId()).get(); //.get() pour un select en base
        //Optional<Manager>  OPTIONAL : O ou 1 element - pour vérifier la nullité ou non de l'element - Optionnal est déjà dans les repositories
        //if(managerFromDataBase.isPresent()) { //.isEmpty //Manager managerDeBase = managerFromDataBase.orElse(newManager)
        //managerFromDataBase.get().getNom() }
        Assertions.assertEquals("Roger", managerFromDataBase.getNom());
    }

    @Test
    public void testFindByName() {
        NewManager newManager = new NewManager();
        newManager.setNom("JeanJean");
        NewManager savedManager = managerDao.save(newManager);
        List<NewManager> managersDontleNom = managerDao.findByNom("JeanJean");
        Assertions.assertEquals(1, managersDontleNom.size());

    }

    @Test
    public void testFindManagerPourAugmentation() {
        NewManager newManager1 = new NewManager();
        newManager1.setNom("Robert");
        newManager1.setSalaire(10_000);
        managerDao.save(newManager1);
        NewManager newManager2 = new NewManager();
        newManager2.setNom("Alderic");
        newManager2.setSalaire(7_000);
        managerDao.save(newManager2);
        NewManager newManager3 = new NewManager();
        newManager3.setNom("Barnabe");
        newManager3.setSalaire(5_000);
        managerDao.save(newManager3);

        List<NewManager> resultats = managerDao.findManagerPourAugmentation(3_000);
        Assertions.assertEquals(0, resultats.size());

        List<NewManager> resultats2 = managerDao.findManagerPourAugmentation(5_000);
        Assertions.assertEquals(0, resultats2.size());

        List<NewManager> resultats3 = managerDao.findManagerPourAugmentation(7_000);
        Assertions.assertEquals(1, resultats3.size());

        List<NewManager> resultats4 = managerDao.findManagerPourAugmentation(11_000);
        Assertions.assertEquals(2, resultats4.size());
    }


    @Test
    public void testFindByTeamNom() {
        NewTeam rocket = new NewTeam();
        rocket.setNom("Team Rocket");
        NewTeam teamSaved = teamDao.save(rocket);

        NewManager newManager = new NewManager();
        newManager.setNom("Giovanni");
        newManager.setTeam(teamSaved);
        NewManager savedManager = managerDao.save(newManager);

        List<NewManager> result = managerDao.findByTeamNom("Team Rocket");
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("Giovanni", result.get(0).getNom());

    }
}
