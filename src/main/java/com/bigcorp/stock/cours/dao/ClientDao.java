package com.bigcorp.stock.cours.dao;

import com.bigcorp.stock.cours.model.Client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Indexed;

import java.util.List;

@Indexed
public interface ClientDao extends CrudRepository<Client, Long> {

    //recherche par nom
    public List<Client> findByLastName(String lastName);
    //recherche par prénom
    public List<Client> findByFirstName(String firstName);

    //recherche par nom insensible à la casse
    public List<Client> findByLastNameIgnoreCase(String lastName);
    //recherche par email insensible à la casse
    public List<Client> findByEmailIgnoreCase(String email);

    //recherche avec une partie du nom
    public List<Client> findByLastNameContaining(String lastName);
    //public List<Client> findByLastNameLike(String lastName); //Il faut rajouter les %
    //recherche avec une partie du nom insensible à la casse
    public List<Client> findByLastNameContainingIgnoreCase(String lastName);

    public List<Client> findByEmailContainingIgnoreCase(String email);

    //recherche prenant en compte deux elements
    public List<Client> findDistinctByLastNameAndFirstName(String lastName, String firstName);
    //recherche prenant en compte l'un ou l'autre element
    public List<Client> findDictinctByLastNameOrFirstName(String lastName, String firstName);

    //METHODS @QUERY - jpql
    //find by lastname
    @Query("select c from Client c where c.lastName = :lastName")
    public List<Client> findNewClientByLastName(@Param("lastName") String lastName);
    //find with 2 attributs
    @Query("select c from Client c where c.lastName = :lastName and c.city = :city")
    public List<Client> findClientByLastNameAndCity(@Param("lastName") String lastName, @Param("city") String city);
    //find client by lastname and order by firstname
    @Query("select c from Client c where c.lastName = :lastName order by c.city desc")
    public List<Client> findClientByLastNameOrderCityDesc(@Param("lastName") String lastName);
    //Bonus : faire une recherche insensible à la casse et par partie du nom.
    @Query("select c from Client c where lower(c.lastName) like lower(concat('%', :lastName, '%'))")
    public List<Client> findNewClientByLastNameIgnoreCase(@Param("lastName") String lastName);

    //jointure avec la Class Premium
    public List<Client> findByPremiumPremium(boolean premium);
    public List<Client> findByPremiumLevel(Integer level);
    public List<Client> findByPremiumName(String name);
}
