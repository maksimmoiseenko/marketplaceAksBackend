package pro.dayx.aks.controllers;

import pro.dayx.aks.models.MenuTypeEntity;
import pro.dayx.aks.services.MenuTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/api")
@CrossOrigin
@RestController
public class MenuTypeController {
    private final MenuTypeService menuTypeService;
    @Autowired
    public MenuTypeController(MenuTypeService menuTypeService){
        this.menuTypeService = menuTypeService;
    }
    @GetMapping("/types")
    public List<MenuTypeEntity> getAllTypes(){
        return menuTypeService.getAllTypes();
    }
    @GetMapping("/type/{id}")
    public MenuTypeEntity getAllTypes(@PathVariable Integer id){
        return menuTypeService.findById(id).get();
    }
}
