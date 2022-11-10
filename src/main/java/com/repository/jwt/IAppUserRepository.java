package com.repository.jwt;

import com.model.jwt.AppUser;
import com.model.DTO.ICountRole;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAppUserRepository extends CrudRepository<AppUser, Long> {
    AppUser findByName(String name); //Tim kiem user co ton tai trong db khong?

    @Query(nativeQuery = true, value = "select r.name, count(user.name) as 'number' from user join user_app_role ur on user.id = ur.app_user_id join role r on r.id = ur.app_role_id group by r.name;")
    Iterable<ICountRole> getRoleNumber();
}