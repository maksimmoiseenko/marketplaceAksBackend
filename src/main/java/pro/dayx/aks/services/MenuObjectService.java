package pro.dayx.aks.services;


import org.springframework.web.multipart.MultipartFile;
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
    private final AzureService azureService;
    @Autowired
    public MenuObjectService(MenuObjectRepository menuObjectRepository, AzureService azureService){
        this.menuObjectRepository = menuObjectRepository;
        this.azureService = azureService;
    }
    public String getAllByType(MenuTypeEntity menuTypeEntity){
        return menuObjectRepository.findAllByMenuTypeEntityOrderById(menuTypeEntity).toString();
    }
    public Optional<MenuObjectEntity> getByObjectId(Integer id){
        return menuObjectRepository.findById(id);
    }

    public void uploadObjectImage(int id, MultipartFile file){
        String avatarsPath = "photos";
        String filePath = this.azureService.uploadFile(file, avatarsPath, "marketplace");
        Optional<MenuObjectEntity> objectOptional = menuObjectRepository.findById(id);
        if(objectOptional.isPresent()){
            objectOptional.get().setImage_url(this.azureService.getFileUrl(filePath, "marketplace"));
            menuObjectRepository.save(objectOptional.get());
        }
    }
}
