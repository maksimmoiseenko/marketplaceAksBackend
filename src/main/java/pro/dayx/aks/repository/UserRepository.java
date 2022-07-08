package pro.dayx.aks.repository;

import java.util.Optional;

import pro.dayx.aks.security.services.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
	Optional<UserEntity> findByEmail(String email);

	Boolean existsByEmail(String email);
}
