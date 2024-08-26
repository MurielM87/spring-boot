package com.bigcorp.stock.cours;

import com.bigcorp.stock.cours.dao.ClientDao;
import com.bigcorp.stock.cours.dao.PremiumDao;
import com.bigcorp.stock.cours.model.Client;
import com.bigcorp.stock.cours.model.Premium;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ClientDaoTest {
    @Autowired
    ClientDao clientDao;
    @Autowired
    PremiumDao premiumDao;

    private Client client1;
    private Client client2;
    private Client client3;
    private Client client4;
    private Client client5;
    private Client client6;

    @BeforeEach
    public void setUp() {
        //initialiser et sauvegarder nouveaux clients
        client1 = new Client();
        client1.setLastName("Picsou");
        client1.setFirstName("Balthazar");
        client1.setCity("Donaldville");

        client2 = new Client();
        client2.setLastName("Duck");
        client2.setFirstName("Donald");
        client2.setCity("Donaldville");

        client3 = new Client();
        client3.setLastName("Duck");
        client3.setFirstName("Riri");
        client3.setCity("Donaldville");

        client4 = new Client();
        client4.setLastName("Duck");
        client4.setFirstName("Daisy");
        client4.setCity("Donaldville");

        client5 = new Client();
        client5.setLastName("Duck");
        client5.setFirstName("Popop");
        client5.setCity("Donaldville");

        client6 = new Client();
        client6.setLastName("Duck");
        client6.setFirstName("Loulou");
        client6.setCity("Mickeyville");

        // Save clients to the database
        clientDao.save(client1);
        clientDao.save(client2);
        clientDao.save(client3);
        clientDao.save(client4);
        clientDao.save(client5);
        clientDao.save(client6);
    }

    @AfterEach
    public void tearDown() {
        // Delete all clients from the database
        clientDao.deleteAll();
    }


    @Test
    public void testFindClientById() {
        Client clientSaved = clientDao.save(client1);
        Assertions.assertEquals(client1.getId(), clientSaved.getId());
        Assertions.assertEquals(client1.getFirstName(), clientSaved.getFirstName());
        Assertions.assertEquals(client1.getLastName(), clientSaved.getLastName());
        Assertions.assertEquals(client1.getCity(), clientSaved.getCity());
        //find a client by id
        Optional<Client> clientInBddRegistred = clientDao.findById(clientSaved.getId());
        Assertions.assertTrue(clientInBddRegistred.isPresent());
        Assertions.assertEquals(clientSaved.getLastName(), clientInBddRegistred.get().getLastName());
        Client clientInBD = clientInBddRegistred.get();
        Assertions.assertEquals(clientSaved.getId(), clientInBD.getId());
        Assertions.assertEquals(clientSaved.getCity(), clientInBD.getCity());
    }

    @Test
    public void testDeleteClientById() {
        //find a client by id
        Optional<Client> clientInBddRegistred = clientDao.findById(client6.getId());
        Assertions.assertTrue(clientInBddRegistred.isPresent());
        Client clientInBD = clientInBddRegistred.get();
        Assertions.assertEquals(client6.getId(), clientInBD.getId());
        //delete a client by id
        clientDao.deleteById(clientInBD.getId());
        Optional<Client> deletedClientRegistred = clientDao.findById(clientInBD.getId());
        Assertions.assertTrue(deletedClientRegistred.isEmpty());
    }

    @Test
    public void testFindClientByLastName() {
        List<Client> clientRegistred = clientDao.findByLastName("Picsou");
        Assertions.assertEquals(1, clientRegistred.size());
    }

    @Test
    public void testFindClientByFirstName() {
        List<Client> clientRegistred = clientDao.findByFirstName("Popop");
        Assertions.assertEquals(1, clientRegistred.size());
    }

    @Test
    public void testFindClientByLastNameIgnoreCase() {
        List<Client> clientRegistred = clientDao.findByLastNameIgnoreCase("picsou");
        Assertions.assertEquals(1, clientRegistred.size());
    }

    @Test
    public void testFindClientByLastNameLike() {
        List<Client> clientRegistred = clientDao.findByLastNameContaining("ck");
        Assertions.assertEquals(5, clientRegistred.size());
    }

    @Test
    public void testFindClientByLastNameContainingIgnoreCase() {
        List<Client> clientRegistred = clientDao.findByLastNameContainingIgnoreCase("du");
        Assertions.assertEquals(5, clientRegistred.size());
    }

    @Test
    public void testFindClientByLastNameAndFirstName() {
        List<Client> clientRegistred = clientDao.findDistinctByLastNameAndFirstName("Duck", "Daisy");
        Assertions.assertEquals(1, clientRegistred.size());
    }

    @Test
    public void testFindClientByLastNameOrFirstName() {
        List<Client> clientRegistred = clientDao.findDictinctByLastNameOrFirstName("Mouse", "Popop");
        Assertions.assertEquals(1, clientRegistred.size());
    }

    @Test
    public void testFindNewClientByLastName() {
        List<Client> clientRegistred = clientDao.findNewClientByLastName("Picsou");
        Assertions.assertEquals(1, clientRegistred.size());
    }

    @Test
    public void testFindClientByLastNameAndCity() {
        List<Client> clientRegistred = clientDao.findClientByLastNameAndCity("Duck", "Donaldville");
        Assertions.assertEquals(4, clientRegistred.size());
    }

    @Test
    public void testFindClientByLastNameOrderCityDesc() {
        List<Client> clientRegistred = clientDao.findClientByLastNameOrderCityDesc("Duck");
        Assertions.assertEquals(5, clientRegistred.size());
        Assertions.assertEquals("Mickeyville", clientRegistred.get(0).getCity());
        Assertions.assertEquals("Donaldville", clientRegistred.get(1).getCity());
    }

    @Test
    public void testFindByPremiumName() {
        Premium argent = new Premium();
        argent.setName("argent");
        Premium premiumSaved = premiumDao.save(argent);

        Client client7 = new Client();
        client7.setLastName("Duck");
        client7.setFirstName("Fifi");
        client7.setPremium(premiumSaved);
        clientDao.save(client7);

        List<Client> result = clientDao.findByPremiumName("argent");
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("Fifi", result.get(0).getFirstName());
    }

    @Test
    public void testFindByPremiumBoolean() {
        Premium or = new Premium();
        or.setPremium(true);
        Premium premiumSaved = premiumDao.save(or);

        Client client8 = new Client();
        client8.setLastName("Duck");
        client8.setFirstName("Grand-Mere");
        client8.setPremium(premiumSaved);
        clientDao.save(client8);
        premiumDao.save(or);

        List<Client> resultats = clientDao.findByPremiumPremium(true);
        Assertions.assertFalse(resultats.isEmpty());
    }

    @Test
    public void testFindByPremiumLevel() {
        Premium premium = new Premium();
        premium.setPremium(true);
        premium.setLevel(1);
        Premium premiumSaved = premiumDao.save(premium);

        Client client9 = new Client();
        client9.setLastName("Mouse");
        client9.setFirstName("Minnie");
        client9.setPremium(premiumSaved);
        clientDao.save(client9);
        premiumDao.save(premium);

        List<Client> resultats = clientDao.findByPremiumLevel(1);
        Assertions.assertEquals(premiumSaved.getLevel(), client9.getPremium().getLevel());
    }

}
