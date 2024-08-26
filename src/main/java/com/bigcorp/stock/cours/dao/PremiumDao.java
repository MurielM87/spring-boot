package com.bigcorp.stock.cours.dao;

import com.bigcorp.stock.cours.model.Premium;
import org.springframework.data.repository.CrudRepository;

public interface PremiumDao extends CrudRepository<Premium, Long> {
}
