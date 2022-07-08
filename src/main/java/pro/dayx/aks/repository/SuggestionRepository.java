package pro.dayx.aks.repository;

import pro.dayx.aks.models.SuggestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SuggestionRepository extends JpaRepository<SuggestionEntity, Long> {
    List<SuggestionEntity> findAllByMenuObjectEntityId(Integer id);

}
