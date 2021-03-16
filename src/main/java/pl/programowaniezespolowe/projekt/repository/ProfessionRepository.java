package pl.programowaniezespolowe.projekt.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.programowaniezespolowe.projekt.model.Profession;

import java.util.Optional;

@Repository
public interface ProfessionRepository extends CrudRepository<Profession, Long> {
    Optional<Profession> findByCode(String code);
}
