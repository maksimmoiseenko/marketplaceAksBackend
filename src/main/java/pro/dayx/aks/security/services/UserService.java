package pro.dayx.aks.security.services;

import pro.dayx.aks.payload.request.UserRequest;
import pro.dayx.aks.payload.response.MessageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pro.dayx.aks.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<UserEntity> user = userRepository.findByEmail(email);
		if(user.isPresent()) return user.get();
		else throw new UsernameNotFoundException("User Not Found with username: " + email);
	}

	public ResponseEntity<?> saveUserInfo(UserRequest userRequest, Long userId){
		Optional<UserEntity> userOptional = userRepository.findById(userId);
		if(userOptional.isPresent()){
			UserEntity user = userOptional.get();
			if(userRequest.getFirstname() != null)
				user.setFirstname(userRequest.getFirstname());
			if(userRequest.getLastname() != null)
				user.setLastname(userRequest.getLastname());
			if(userRequest.getPatronymic() != null)
				user.setPatronymic(userRequest.getPatronymic());
			if(userRequest.getSex() != null)
				user.setSex(userRequest.getSex());
			logger.info("User with email has been edited: " + user.getEmail());
			return ResponseEntity.ok(userRepository.save(user));
		}
		else return ResponseEntity.badRequest().body(new MessageResponse("Error: User not found!"));
	}
	public ResponseEntity<?> getUser(Long id){
		Optional<UserEntity> user = userRepository.findById(id);
		if(user.isPresent()) return ResponseEntity.ok(user.get());
		else return ResponseEntity.badRequest().body(new MessageResponse("Error: User not Found!"));
	}
	public void supplierAddOffer(Long supplierId, int objectId){

	}
}
