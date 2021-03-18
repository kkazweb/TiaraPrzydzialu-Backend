package pl.programowaniezespolowe.projekt.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.programowaniezespolowe.projekt.model.ElementaryGroup;

import java.util.Optional;

@Repository
public interface ElementaryGroupRepository extends CrudRepository<ElementaryGroup, Long> {
    Optional<ElementaryGroup> findByCode(String code);
}
