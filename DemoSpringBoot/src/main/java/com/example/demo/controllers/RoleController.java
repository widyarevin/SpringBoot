package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.models.Role;
import com.example.demo.services.RoleService;

import io.micrometer.core.lang.Nullable;

@Controller
@RequestMapping("role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    public String index(Model model){
        model.addAttribute("role", roleService.getAll());
        return "role/index";
    }
    
    @GetMapping(value = {"form", "form/{Id}"})
    public String  regionForm(Model model, @PathVariable (required = false) Integer Id){
        if (Id != null){
            model.addAttribute("role", roleService.getById(Id));
        } else {
            model.addAttribute("role", new Role());
        }
        return "role/form";
    }

    @PostMapping("save")
    public String save(@Nullable Role role){
        Boolean result;
        if(role.getId()==null){
            result = roleService.save(role);
        } else {
            result = roleService.save(role);
        } if (result){
            return "redirect:/role";
        }else {
            return "role/form";
        }
    }

  
    @PostMapping("delete/{id}")
    public String delete(@PathVariable Integer id){
        roleService.delete(id);
            return "redirect:/role";
    }
    
}
