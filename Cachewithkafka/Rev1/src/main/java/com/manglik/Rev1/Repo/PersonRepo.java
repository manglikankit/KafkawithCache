package com.manglik.Rev1.Repo;

import com.manglik.Rev1.Entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepo extends JpaRepository<Person, Integer> {

    @Query(value = "SELECT email FROM person WHERE id =:id", nativeQuery = true)
    public String getPersonEmailById(@Param("id") int id);

    @Query(value = "SELECT email WHEN COUNT(p) > 0 THEN TRUE ELSE FALSE END FROM person p WHERE p.id =:id", nativeQuery = true)
    public Boolean isPersonExistById(@Param("id") int id);

}
