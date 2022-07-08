package pro.dayx.aks.services;


import pro.dayx.aks.models.MenuObjectEntity;
import pro.dayx.aks.models.MenuTypeEntity;
import pro.dayx.aks.repository.MenuObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuObjectService {
    private final MenuObjectRepository menuObjectRepository;
    @Autowired
    public MenuObjectService(MenuObjectRepository menuObjectRepository){
        this.menuObjectRepository = menuObjectRepository;
    }
    public List<MenuObjectEntity> getAllByType(MenuTypeEntity menuTypeEntity){
        return menuObjectRepository.findAllByMenuTypeEntityOrderById(menuTypeEntity);
    }
    public Optional<MenuObjectEntity> getByObjectId(Integer id){
        return menuObjectRepository.findById(id);
    }
}
