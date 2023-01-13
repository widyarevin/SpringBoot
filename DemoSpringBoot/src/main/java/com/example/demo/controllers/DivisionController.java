package com.example.demo.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.demo.models.Division;
import com.example.demo.services.DivisionService;
import com.example.demo.services.RegionService;
import io.micrometer.core.lang.Nullable;

@Controller
@RequestMapping("/division")
public class DivisionController {

    @Autowired
    private DivisionService divisionService;
    @Autowired
    private RegionService regionService;


    @GetMapping
    public String index(Model model){
       model.addAttribute("divv", divisionService.getAll());
       return "division/index";
    }

    @GetMapping(value = {"form", "form/{id}"})
    public String  regionForm(Model model, @PathVariable (required = false) Integer id){
        if (id != null){
            model.addAttribute("regions", regionService.getAll());
            model.addAttribute("division", divisionService.getById(id));
        } else {
            model.addAttribute("regions", regionService.getAll());
            model.addAttribute("division", new Division());
        }
        return "division/form";
    }

    @PostMapping("save")
    public String save(@Nullable Division division){
        Boolean result;
        if(division.getId() != null){
            result = divisionService.save(division);
        } else {
            result = divisionService.save(division);
        } 
        
        if (result){
            return "redirect:/division";
        }else {
            return "division/form";
        }
    }

    @PostMapping("delete/{id}")
    public String delete(@PathVariable Integer id){
        divisionService.delete(id);
            return "redirect:/division";
    }

}
