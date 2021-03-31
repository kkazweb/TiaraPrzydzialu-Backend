package pl.programowaniezespolowe.projekt.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.programowaniezespolowe.projekt.model.ElementaryGroup;

import java.util.List;
import java.util.Optional;

@Repository
public interface ElementaryGroupRepository extends CrudRepository<ElementaryGroup, Long>, JpaSpecificationExecutor<ElementaryGroup> {
    Optional<ElementaryGroup> findByCode(String code);
    List<ElementaryGroup> findAll();
    List<ElementaryGroup> findAllByNameContainsIgnoreCaseOrCodeContainsIgnoreCase(String name, String code);
}
