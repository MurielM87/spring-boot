package com.bigcorp.stock.cours.dao;

import com.bigcorp.stock.cours.model.NewTeam;
import org.springframework.data.repository.CrudRepository;

public interface TeamNewDao extends CrudRepository<NewTeam, Long> {
}
