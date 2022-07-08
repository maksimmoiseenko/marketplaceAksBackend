package pro.dayx.aks.services;

import pro.dayx.aks.models.OrderEntity;
import pro.dayx.aks.models.SuggestionEntity;
import pro.dayx.aks.payload.response.MessageResponse;
import pro.dayx.aks.repository.OrderRepository;
import pro.dayx.aks.repository.SuggestionRepository;
import pro.dayx.aks.repository.UserRepository;
import pro.dayx.aks.security.services.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final SuggestionRepository suggestionRepository;
    private final UserRepository userRepository;
    @Autowired
    public OrderService(OrderRepository orderRepository, SuggestionRepository suggestionRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.suggestionRepository = suggestionRepository;
        this.userRepository = userRepository;
    }

    public ResponseEntity<?> getOrdersByUserId(Long id){
        System.out.println(this.orderRepository.findAllByUserEntityId(id).toString());
        return ResponseEntity.ok(this.orderRepository.findAllByUserEntityId(id).toString());
    }

    public void deleteById(Long orderId) {
        this.orderRepository.deleteById(orderId);
    }

    public ResponseEntity<?> addOrder(Long suggestionId, UserDetails userDetails) {
        UserEntity userEntity = userRepository.findByEmail(userDetails.getUsername()).get();
        if(!userEntity.getRole().equals("ROLE_CLIENT"))
            return ResponseEntity.badRequest().body(new MessageResponse("Role is not Client"));
        Optional<SuggestionEntity> suggestionOptional = suggestionRepository.findById(suggestionId);
        if(suggestionOptional.isPresent()){
            OrderEntity order = new OrderEntity(userEntity, suggestionOptional.get());
            return ResponseEntity.ok(orderRepository.save(order));
        }
        else return ResponseEntity.badRequest().body(new MessageResponse("Suggestion is not found"));

    }
}
