package com.primus.eazyschool.repository;

import com.primus.eazyschool.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person,Integer> {
    Person findByEmail(String email);
}
