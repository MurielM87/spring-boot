package com.bigcorp.stock.cours.service;

import com.bigcorp.stock.cours.dao.ClientDao;
import com.bigcorp.stock.cours.model.Client;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientDao clientDao;
    @Autowired
    private PremiumService premiumService;

    @Transactional
    public Client save(Client client) {
        Client clientSaved = this.clientDao.save(client);
        if(client.getPremium() != null) {
            this.premiumService.save(client.getPremium());
        }
        return clientSaved;
    }

    public Client findById(Long id) {
        Optional<Client> optionalClient = this.clientDao.findById(id);
        return optionalClient.orElse(null);
    }
    public List<Client> findAll() {
        return (List<Client>) this.clientDao.findAll();
    }

    @Transactional
    public void deleteById(Long id) {
        Optional<Client> optionalClient = this.clientDao.findById(id);
        if(optionalClient.isPresent()) {
            this.clientDao.deleteById(id);
        }
    }

}
