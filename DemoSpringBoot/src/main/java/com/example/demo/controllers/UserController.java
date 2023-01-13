package com.example.demo.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.models.Employee;
import com.example.demo.services.UserService;
import io.micrometer.core.lang.Nullable;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    // @Autowired
    // private RoleService roleService;

    @GetMapping
    public String index(Model model){
        model.addAttribute("user", userService.getAll());
        // model.addAttribute("role", roleService.getIdByLevel()); // untuk debug
        return "user/index";
    }
    
    @GetMapping(value = {"registrasi"})
    public String  regionForm(Model model){
            model.addAttribute("user", new User());
            model.addAttribute("employee", new Employee());
        return "user/registrasi";
    }

    @PostMapping("save")
    public String save(@Nullable User user, Employee employee){
        Boolean result;
        if(user.getId()==null){
            result = userService.save(user,employee);
        } else {
            result = userService.save(user,employee);
        } if (result){
            return "redirect:/user/login";
        }else {
            return "user/registrasi";
        }
    }

    @GetMapping("login")
    public String login(Model model){
        model.addAttribute("user", new User());
        return "user/login";
    }

    @PostMapping("userLogin")
    public String userLogin(@ModelAttribute("user") User user){
        // String email = user.getEmployee().getEmail();
        Integer id = user.getId();
        User userData = userService.getById(id);
        if(user.getPassword().equals(userData.getPassword())  ){
            return "employee/hello";
        } else {
            return "user/login";
        }
        
    }
    
    
}
