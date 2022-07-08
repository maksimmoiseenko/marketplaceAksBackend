package pro.dayx.aks.repository;


import pro.dayx.aks.models.MenuObjectEntity;
import pro.dayx.aks.models.MenuTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuObjectRepository extends JpaRepository<MenuObjectEntity,Integer> {
    List<MenuObjectEntity> findAllByMenuTypeEntityOrderById(MenuTypeEntity menuTypeEntity);
}
