package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.demo.models.User;
import com.example.demo.DTO.user.LoginDTO;
import com.example.demo.DTO.user.RegisterDTO;
import com.example.demo.models.Employee;
import com.example.demo.services.UserService;
import io.micrometer.core.lang.Nullable;

@Controller
@RequestMapping("userM")
public class UserManagementController {

    @Autowired
    private UserService userService;
    
    @GetMapping(value = {"registrasi"})
    public String  regisForm(Model model){
            model.addAttribute("user", new User());
            model.addAttribute("employee", new Employee());
        return "UserManagement/registrasi";
    }

    @GetMapping(value = {"registerasiDTO"})
    public String  registerForm(Model model){
            model.addAttribute("regDTO", new RegisterDTO());
        return "UserManagement/registerasiDTO";
    }

    @PostMapping("save")
    public String save(@Nullable User user, Employee employee){
        Boolean result;
            result = userService.save(user,employee);
            if (result){
            return "redirect:/userM/login";
            }else {
            return "userM/registrasi";
            }
    }

    @PostMapping("registerDTO")
    public String submitRegisterasi(@Nullable User user, Employee employee){
        Boolean result;
            result = userService.save(user,employee);
            if (result){
            return "redirect:/userM/login";
            }else {
            return "userM/registrasi";
            }
    }

    @GetMapping("login")
    public String login(Model model){
        model.addAttribute("userDTO", new LoginDTO());
        return "UserManagement/login";
    }

    @PostMapping("userLogin")
    public String userLogin(User user, Employee employee, Model model){
        // String email = user.getEmployee().getEmail();
        // Integer id = user.getId();
        // User userData = userService.getById(id);
        // if(user.getPassword().equals(userData.getPassword())  ){
        //     return "employee/hello";
        // } else {
        //     return "user/login";
        // }
        // User userData = userService.getByEmail(user.getEmployee().getEmail());

        User userData = userService.LoginEmp(user.getEmployee().getEmail(), user.getPassword());
        if(userData != null){
            return "redirect:/employee";
        } else {
            return "redirect:/UserManagement/login";
        }
    }

    @PostMapping("userLoginDto")
    public String userLoginDto(LoginDTO loginDTO, Model model){
        boolean userData = userService.loginDTO(loginDTO);
        // model.addAttribute("users", userService.getAll()); //Keperluan debug
        // System.out.println("Datanya " + userService.getAll()); //Keperluan debug
        if(userData == true){
            // model.addAttribute("users", userService.getIdByEmail(null)) // Login dengan profil sendiri, belum selesai
            return "redirect:/employee";
        } else {
            model.addAttribute("user", new LoginDTO());
            return "redirect:/UserManagement/login";
        }
    }

    @GetMapping("forgotPassword")
    public String forgotPass(){
            return "UserManagement/forgotPassword";
        }
    
     @GetMapping("changePassword")
    public String changePass(Model model){
        model.addAttribute("user", new User());
            return "UserManagement/changePassword";
        }
    
    @PostMapping("changePw")
    public String changePass(User user, Employee email){
        userService.changePass(user.getPassword(), user.getEmployee().getEmail());
                return "UserManagement/login";
          
        }
    
    
}
