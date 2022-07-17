package pro.dayx.aks.services;

import pro.dayx.aks.models.MenuObjectEntity;
import pro.dayx.aks.models.MenuTypeEntity;
import pro.dayx.aks.models.SuggestionEntity;
import pro.dayx.aks.payload.request.SuggestionAndObjectRequest;
import pro.dayx.aks.payload.response.MessageResponse;
import pro.dayx.aks.repository.MenuObjectRepository;
import pro.dayx.aks.repository.MenuTypeRepository;
import pro.dayx.aks.repository.SuggestionRepository;
import pro.dayx.aks.repository.UserRepository;
import pro.dayx.aks.security.services.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SuggestionService {
    private static final Logger logger = LoggerFactory.getLogger(SuggestionService.class);

    private final SuggestionRepository suggestionRepository;
    private final UserRepository userRepository;
    private final MenuObjectRepository menuObjectRepository;
    private final MenuTypeRepository menuTypeRepository;
    @Autowired
    public SuggestionService(MenuTypeRepository menuTypeRepository, SuggestionRepository suggestionRepository, UserRepository userRepository, MenuObjectRepository menuObjectRepository){
        this.suggestionRepository = suggestionRepository;
        this.menuObjectRepository = menuObjectRepository;
        this.userRepository = userRepository;
        this.menuTypeRepository = menuTypeRepository;
    }

    public ResponseEntity<?> addSuggestion(UserDetails userDetails, Integer menuObjectId, Double price){
        Optional<MenuObjectEntity> menuObjectEntity = menuObjectRepository.findById(menuObjectId);
        UserEntity userEntity = userRepository.findByEmail(userDetails.getUsername()).get();
        if(!userEntity.getRole().equals("ROLE_SUPPLIER"))
            return ResponseEntity.badRequest().body(new MessageResponse("Role is not Supplier"));
        if(menuObjectEntity.isPresent()){
            SuggestionEntity suggestionEntity = new SuggestionEntity();
            suggestionEntity.setUserEntity(userEntity);
            suggestionEntity.setMenuObjectEntity(menuObjectEntity.get());
            suggestionEntity.setPrice(price);
            logger.info("suggestion is added");
            return ResponseEntity.ok(suggestionRepository.save(suggestionEntity));
        }
        else return ResponseEntity.badRequest().body(new MessageResponse("MenuObject is not found"));
    }
    public ResponseEntity<?> getSuggestionsByMenuObjectId(int objectId){
        List<SuggestionEntity> list = suggestionRepository.findAllByMenuObjectEntityId(objectId);
        System.out.println(list.toString());
        return ResponseEntity.ok(list.toString());
    }
    public void deleteSuggestion(Long id){
        logger.info("suggestion with id " + id + " is deleted");
        this.suggestionRepository.deleteById(id);
    }

    public ResponseEntity<?> addSuggestionAndObject(UserDetails userDetails, SuggestionAndObjectRequest suggestionRequest) {
        Optional<MenuTypeEntity> menuTypeEntity = menuTypeRepository.getMenuTypeEntityByName(suggestionRequest.getCategory());
        UserEntity userEntity = userRepository.findByEmail(userDetails.getUsername()).get();
        if(!userEntity.getRole().equals("ROLE_SUPPLIER"))
            return ResponseEntity.badRequest().body(new MessageResponse("Role is not Supplier"));
        if(!menuTypeEntity.isPresent()){
            return ResponseEntity.badRequest().body(new MessageResponse("MenuTypeEntity is now found"));
        }
        MenuObjectEntity menuObjectEntity = menuObjectRepository.save(new MenuObjectEntity(menuTypeEntity.get(), suggestionRequest.getName(), suggestionRequest.getDescription(), suggestionRequest.getMerchandiseOrService()));
        SuggestionEntity suggestionEntity = new SuggestionEntity(userEntity, menuObjectEntity,suggestionRequest.getPrice(), suggestionRequest.getCurrency(), suggestionRequest.getPresence(), suggestionRequest.getUnit(), suggestionRequest.getDescription(), suggestionRequest.getAmountOfDays(), suggestionRequest.getLeft());
        return ResponseEntity.ok().body(suggestionRepository.save(suggestionEntity));
    }

    public ResponseEntity<?> getSuggestion(Long id) {
        Optional<SuggestionEntity> suggestionOptional = this.suggestionRepository.findById(id);
        if(suggestionOptional.isPresent()) return ResponseEntity.ok(suggestionOptional.get().toString());
        else return ResponseEntity.badRequest().body("Suggestion is not found");
    }
}
