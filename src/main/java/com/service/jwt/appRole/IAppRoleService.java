package com.service.jwt.appRole;

import com.model.jwt.AppRole;
import com.service.IGeneralService;

public interface IAppRoleService extends IGeneralService<AppRole> {
    public AppRole findByName(String name);
}
