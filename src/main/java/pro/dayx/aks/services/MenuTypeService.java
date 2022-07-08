package pro.dayx.aks.services;

import pro.dayx.aks.models.MenuTypeEntity;
import pro.dayx.aks.repository.MenuTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuTypeService {
    private final MenuTypeRepository menuTypeRepository;
    @Autowired
    public MenuTypeService(MenuTypeRepository menuTypeRepository){
        this.menuTypeRepository = menuTypeRepository;
    }
    public List<MenuTypeEntity> getAllTypes(){
        return menuTypeRepository.findAll();
    }
    public Optional<MenuTypeEntity> findById(Integer id){
        return menuTypeRepository.findById(id);
    }
}
