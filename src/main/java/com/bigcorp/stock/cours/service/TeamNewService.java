package com.bigcorp.stock.cours.service;

import com.bigcorp.stock.cours.dao.TeamNewDao;
import com.bigcorp.stock.cours.model.NewTeam;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamNewService {

    @Autowired
    private TeamNewDao teamDao;

    @Transactional
    public NewTeam save(NewTeam team) {
        return this.teamDao.save(team);
    }
}
