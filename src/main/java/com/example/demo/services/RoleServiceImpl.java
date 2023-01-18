package com.example.demo.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repositories.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<com.example.demo.models.Role> getAll() {
        return roleRepository.findAll();
    }

    @Override
    public com.example.demo.models.Role getById(Integer id) {
        return roleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Role tidak ditemukan"));
    }

    @Override
    public boolean save(com.example.demo.models.Role role) {
        roleRepository.save(role);
        return roleRepository.findById(role.getId()).isPresent();
    }

    @Override
    public boolean delete(Integer id) {
        roleRepository.deleteById(id);
        return !roleRepository.findById(id).isPresent();
    }

    @Override
    public int getIdByLevel() {
        return roleRepository.findMaxLevel();
    }
    
}
