package pl.programowaniezespolowe.projekt.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.programowaniezespolowe.projekt.model.Profession;

import java.util.List;

@Repository
public interface ProfessionRepository extends CrudRepository<Profession, Long>, JpaSpecificationExecutor<Profession> {
    List<Profession> findAllByNameContainsIgnoreCaseOrCodeStartsWith(String phrase, String code);
    List<Profession> findAllByNameStartsWith(String letter);
}
