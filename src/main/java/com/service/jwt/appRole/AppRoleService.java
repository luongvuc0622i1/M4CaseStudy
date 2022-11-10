package com.service.jwt.appRole;

import com.model.jwt.AppRole;
import com.repository.jwt.IAppRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppRoleService implements IAppRoleService{
    @Autowired
    private IAppRoleRepository roleRepository;

    @Override
    public Iterable<AppRole> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<AppRole> findById(Long id) {
        return roleRepository.findById(id);
    }

    @Override
    public AppRole save(AppRole model) {
        return roleRepository.save(model);
    }

    @Override
    public void remove(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public AppRole findByName(String name) {
        return roleRepository.findByName(name);
    }
}
