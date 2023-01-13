package com.example.demo.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.demo.services.EmployeeService;
import com.example.demo.services.RoleService;
import com.example.demo.services.UserService;

@Controller
@RequestMapping("employee")
public class EmployeeController {
    
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    
    @GetMapping()
    public String index(Model model){
        model.addAttribute("employee", employeeService.getAll());
        model.addAttribute("user", userService.getAll());
        model.addAttribute("role", roleService.getAll());
        return "employee/index";
    }

}
