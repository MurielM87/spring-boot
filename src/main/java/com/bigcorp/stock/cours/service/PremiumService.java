package com.bigcorp.stock.cours.service;

import com.bigcorp.stock.cours.dao.PremiumDao;
import com.bigcorp.stock.cours.model.Premium;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PremiumService {
    @Autowired
    private PremiumDao premiumDao;

    @Transactional
    public Premium save(Premium premium) {
        return this.premiumDao.save(premium);
    }

}
