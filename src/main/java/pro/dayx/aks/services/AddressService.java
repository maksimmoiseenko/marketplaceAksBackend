package pro.dayx.aks.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import pro.dayx.aks.models.AddressEntity;
import pro.dayx.aks.payload.request.AddressRequest;
import pro.dayx.aks.repository.AddressRepository;
import pro.dayx.aks.repository.UserRepository;
import pro.dayx.aks.security.services.UserEntity;

import java.util.Optional;

@Service
public class AddressService {
    private final AddressRepository addressRepository;
    private final UserRepository userRepository;
    @Autowired
    public AddressService(AddressRepository addressRepository, UserRepository userRepository){
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
    }

    public ResponseEntity<?> getUserAddresses(UserDetails userDetails) {
        Optional<UserEntity> userEntityOptional = userRepository.findByEmail(userDetails.getUsername());
        if(userEntityOptional.isPresent())
            return ResponseEntity.ok(addressRepository.findAllByClientId(userEntityOptional.get().getId()));
        else return ResponseEntity.badRequest().body("User is not found");
    }

    public ResponseEntity<?> addAddress(UserDetails details, AddressRequest addressRequest) {
        Optional<UserEntity> userEntityOptional = userRepository.findByEmail(details.getUsername());
        if(userEntityOptional.isPresent()){
            AddressEntity addressEntity = new AddressEntity();
            addressEntity.setLocality(addressRequest.getLocality());
            addressEntity.setStreet(addressRequest.getStreet());
            addressEntity.setHouseNumber(addressRequest.getHouseNumber());
            addressEntity.setBuilding(addressRequest.getBuilding());
            addressEntity.setEntrance(addressRequest.getEntrance());
            addressEntity.setFloor(addressRequest.getFloor());
            addressEntity.setApartment(addressRequest.getApartment());
            addressEntity.setComment(addressRequest.getComment());
            addressEntity.setFirstname(addressRequest.getFirstname());
            addressEntity.setLastname(addressRequest.getLastname());
            addressEntity.setClient(userEntityOptional.get());
            addressEntity.setEmail(addressRequest.getEmail());
            addressEntity.setPhone(addressRequest.getPhone());
            addressEntity.setClient(userEntityOptional.get());
            return ResponseEntity.ok(addressRepository.save(addressEntity));
        }
        else return ResponseEntity.badRequest().body("User is not found");
    }
}
