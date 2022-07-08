package pro.dayx.aks.repository;

import pro.dayx.aks.models.MenuTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MenuTypeRepository extends JpaRepository<MenuTypeEntity, Integer> {
    MenuTypeEntity getMenuTypeEntityById(Integer id);
    Optional<MenuTypeEntity> getMenuTypeEntityByName(String name);
}
