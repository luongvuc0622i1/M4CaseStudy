package com.repository;

import com.model.AppUser;
import com.model.DTO.ICountRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByName(String name);

    @Query(nativeQuery = true, value = "select r.name, count(app_user.name) as 'number' from app_user join user_roles ur on app_user.id = ur.user_id join role r on r.id = ur.role_id group by r.name;")
    Iterable<ICountRole> getRoleNumber();
}