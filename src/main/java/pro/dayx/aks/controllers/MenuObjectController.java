package pro.dayx.aks.controllers;


import pro.dayx.aks.models.MenuObjectEntity;
import pro.dayx.aks.models.MenuTypeEntity;
import pro.dayx.aks.payload.response.MessageResponse;
import pro.dayx.aks.services.MenuObjectService;
import pro.dayx.aks.services.MenuTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RequestMapping("/api")
@CrossOrigin
@RestController
public class MenuObjectController {
    private final MenuObjectService menuObjectService;
    private final MenuTypeService menuTypeService;
    @Autowired
    public MenuObjectController(MenuObjectService menuObjectService, MenuTypeService menuTypeService){
        this.menuObjectService = menuObjectService;
        this.menuTypeService = menuTypeService;
    }
    @GetMapping("/objects/type/{id}")
    public List<MenuObjectEntity> getAllByMenuTypeId(@PathVariable Integer id){
        Optional<MenuTypeEntity> typeOptional = menuTypeService.findById(id);
        return typeOptional.map(menuObjectService::getAllByType).orElse(null);
    }
    @GetMapping("/object/{id}")
    public ResponseEntity<?> getObjectById(@PathVariable Integer id){
        Optional<MenuObjectEntity> objectOptional = menuObjectService.getByObjectId(id);
        if(objectOptional.isPresent()) return ResponseEntity.ok(objectOptional.get());
        else return ResponseEntity.badRequest().body(new MessageResponse("Error: Object not found!"));
    }
}
