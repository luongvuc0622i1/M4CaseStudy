package com.repository.jwt;

import com.model.jwt.AppRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAppRoleRepository extends CrudRepository<AppRole, Long> {
    AppRole findByName(String name);
}