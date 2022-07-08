package pro.dayx.aks.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pro.dayx.aks.models.ERole;
import pro.dayx.aks.payload.request.LoginRequest;
import pro.dayx.aks.payload.request.SignupRequest;
import pro.dayx.aks.payload.response.JwtResponse;
import pro.dayx.aks.payload.response.MessageResponse;
import pro.dayx.aks.repository.UserRepository;
import pro.dayx.aks.security.jwt.JwtUtils;
import pro.dayx.aks.security.services.UserEntity;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserEntity userDetails = (UserEntity) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt,
												 userDetails.getId(),
												 userDetails.getEmail(),
												 roles.get(0),
												 userDetails.getFirstname(),
												 userDetails.getLastname(),
												 userDetails.getPatronymic(),
												 userDetails.getSex()));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		UserEntity user = new UserEntity(signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()));

		String strRole = signUpRequest.getRole();
		String role;

		if (strRole == null) {
			role = ERole.ROLE_CLIENT.name();
		} else {
			switch (strRole) {
				case "admin":
					role = ERole.ROLE_ADMIN.name();
					break;
				case "mod":
					role = ERole.ROLE_MODERATOR.name();
					break;
				case "client":
					role = ERole.ROLE_CLIENT.name();
					break;
				case "supplier":
					role = ERole.ROLE_SUPPLIER.name();
					break;
				default:
					role = ERole.ROLE_CLIENT.name();
				}

		}

		user.setRole(role);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}
