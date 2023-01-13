package com.example.demo.services;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.models.Employee;
import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.repositories.EmployeeRepository;
import com.example.demo.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired 
    private EmployeeRepository employeeRepository;
    @Autowired
    private RoleService roleService;

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getById(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User tidak ditemukan"));
    }

    @Override
    public boolean save(User user, Employee employee) {
        Role role = new Role();
        user.setRole(role);
        // role.setId(2);
        role.setId(roleService.getIdByLevel());
        userRepository.save(user);
        employee.setId(user.getId());
        employeeRepository.save(employee);
        return userRepository.findById(user.getId()).isPresent();
    }

    // @Override
    // public User getByEmail(String email) {
    //     return userRepository.find
    // }

    // @Override
    // public boolean save(User user, Employee employee) {
    //     if (user.getId() == employee.getId()){
    //         userRepository.save(user);
    //         employeeRepository.save(employee);
    //     } else throw new IllegalArgumentException("Tidak dapat menambah akun baru");
    //     return userRepository.findById(user.getId()).isPresent();
    // }
    
}
