package com.example.demo.services;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.demo.DTO.user.LoginDTO;
import com.example.demo.DTO.user.RegisterDTO;
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
    @Autowired
    private PasswordEncoder passwordEncoder;

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
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        employee.setId(user.getId());
        employeeRepository.save(employee);
        return userRepository.findById(user.getId()).isPresent();
    }

    @Override
    public User LoginEmp(String email, String password) {
        return userRepository.Login(email, password);
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public boolean changePass(String user, String email) {
        userRepository.updatePass(user, email);
      return  true;
    }

    @Override
    public boolean loginDTO(LoginDTO loginDTO) {
        // User user2 = new User();
        // if(employee.getEmail().equals(loginDTO.getEmail()) && user2.getPassword().equals(loginDTO.getPassword())){
        //     return true;
        // }  else {
        //     throw new RuntimeException("Email / Password!");
        // }
        
        User user = userRepository.findByEmail(loginDTO.getEmail());
        if(user == null){
            throw new RuntimeException("Email does not exist.");
        }
        if(!user.getPassword().equals(loginDTO.getPassword())){
            throw new RuntimeException("Password wrong!");
        }
        return true;
    }

    @Override
    public boolean register(RegisterDTO registerDTO, User user, Employee employee) {
        if (registerDTO.getemail().isEmpty()){
            throw new IllegalArgumentException("Email can not be empty");
        }
        Role role = new Role();
        user.setRole(role);
        role.setId(roleService.getIdByLevel());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        employee.setId(user.getId());
        employeeRepository.save(employee);
        return userRepository.findById(registerDTO.getId()).isPresent();
    }

    @Override
    public Integer getIdByEmail(String email) {
        return userRepository.findIdByEmail(email);
    }

    // @Override
    // public boolean save(User user, Employee employee) {
    //     if (user.getId() == employee.getId()){
    //         userRepository.save(user);
    //         employeeRepository.save(employee);
    //     } else throw new IllegalArgumentException("Tidak dapat menambah akun baru");
    //     return userRepository.findById(user.getId()).isPresent();
    // }
    
}
