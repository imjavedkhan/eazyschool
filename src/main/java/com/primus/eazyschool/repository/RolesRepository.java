package com.primus.eazyschool.repository;

import com.primus.eazyschool.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<Roles,Integer> {
    Roles findByRoleName(String studentRole);
}
