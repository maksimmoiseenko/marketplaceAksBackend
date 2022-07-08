package pro.dayx.aks.repository;

import pro.dayx.aks.models.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findAllByUserEntityId(Long id);
}
