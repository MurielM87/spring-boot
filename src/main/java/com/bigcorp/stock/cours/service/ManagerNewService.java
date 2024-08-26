package com.bigcorp.stock.cours.service;

import com.bigcorp.stock.cours.dao.ManagerNewDao;
import com.bigcorp.stock.cours.model.NewManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ManagerNewService {

    @Autowired
    private ManagerNewDao managerDao;
    @Autowired
    private TeamNewService teamService;

    @Transactional //tout ou rien
    public NewManager save(NewManager manager) {
        if(manager.getTeam().getNom().equals("équipe principale")) {
            manager.setSalaire(manager.getSalaire() + 1000);
        }
        NewManager savedManager = this.managerDao.save(manager);
        this.teamService.save(manager.getTeam());
        return savedManager;
    }

    public NewManager findById(Long id) {
        Optional<NewManager> optionalManager = this.managerDao.findById(id);
        if(optionalManager.isEmpty()) {
            return null;
        }
        return optionalManager.get();
    }
}
