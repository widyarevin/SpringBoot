package com.example.demo.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.demo.models.Region;
import com.example.demo.services.RegionService;
import io.micrometer.core.lang.Nullable;


@Controller
@RequestMapping("/region")
public class RegionController {
    @Autowired
    private RegionService regionService;

    // GET ALL
    // /region
    @GetMapping
    public String index(Model model){
        //Object region = regionDAO.getAll(); //Untuk keperluan debug
        model.addAttribute("a", regionService.getAll());
        return "region/index";
    }

    // CREATE
    // GET
    // /region/form
    @GetMapping(value = {"form", "form/{Id}"})
    public String  regionForm(Model model, @PathVariable (required = false) Integer Id){
        if (Id != null){
            model.addAttribute("region", regionService.getById(Id));
        } else {
            model.addAttribute("region", new Region());
        }
        return "region/form";
    }

    @PostMapping("save")
    public String save(@Nullable Region region){
        Boolean result;
        if(region.getId()==null){
            result = regionService.save(region);
        } else {
            result = regionService.save(region);
        } if (result){
            return "redirect:/region";
        }else {
            return "region/form";
        }
    }

    //POST
    @PostMapping("delete/{id}")
    public String delete(@PathVariable Integer id){
        regionService.delete(id);
            return "redirect:/region";
    }

}
