package com.example.demo.services;
import java.util.List;

import com.example.demo.DTO.user.LoginDTO;
import com.example.demo.DTO.user.RegisterDTO;
import com.example.demo.models.Employee;
import com.example.demo.models.User;

public interface UserService {
    public List<User> getAll();
    public User getById(Integer id);
    public boolean save(User user, Employee employee); //register
    public User LoginEmp(String email, String password);
    public User getByEmail(String email);
    public boolean changePass(String user, String email);
    public boolean loginDTO(LoginDTO loginDTO);
    public boolean register(RegisterDTO registerDTO, User user, Employee employee);
    public Integer getIdByEmail(String email);
}
