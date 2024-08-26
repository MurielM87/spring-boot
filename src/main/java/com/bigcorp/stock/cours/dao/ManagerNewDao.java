package com.bigcorp.stock.cours.dao;

import com.bigcorp.stock.cours.model.NewManager;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManagerNewDao extends CrudRepository<NewManager, Long> { //Long ou int pour la clé - possible hériter de 2 interfaces
    //methods QUERY
    public List<NewManager> findByNom(String nom);
    public List<NewManager> findByPoids(int poids);
    public List<NewManager> findByNomAndPoidsOrderByPoidsDesc(String nom, int poids);

    @Query("select m from Manager m where m.nom != 'Robert' and m.salaire is not null and m.salaire < :salaire")
    public List<NewManager> findManagerPourAugmentation(@Param("salaire") int salaire); //@Param("salaire") correspond au :salaire au-dessus
//    @Query("select m from Manager m where m.nom != :nom and m.salaire is not null and m.salaire < :salaire")
//    public List<Manager> findManagerPourAugmentation(@Param("nom") String nom, @Param("salaire") int salaire); //@Param("salaire") correspond au :salaire au-dessus

    //jointure - recherche de tous les managers par nom de l'equipe
    public List<NewManager> findByTeamNom(String nom); //String nom de la Team

//    public Optional<Manager> findByNumero(String numero);

}
