package com.example.demo.services;
import java.util.List;
import com.example.demo.models.Role;

public interface RoleService {
    public List<Role> getAll();
    public Role getById(Integer id);
    public boolean save(Role role);
    public boolean delete(Integer id);
    public int getIdByLevel();

}
