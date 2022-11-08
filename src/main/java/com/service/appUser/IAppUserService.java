package com.service.appUser;

import com.model.AppUser;
import com.model.DTO.ICountRole;
import com.service.IGeneralService;
import org.springframework.security.core.userdetails.UserDetails;

public interface IAppUserService extends IGeneralService<AppUser> {
    UserDetails loadUserByUsername(String username);

    AppUser findUserByName(String username);

    Iterable<ICountRole> getRoleNumber();
}
