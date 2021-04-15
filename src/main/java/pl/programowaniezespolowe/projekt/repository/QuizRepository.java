package pl.programowaniezespolowe.projekt.repository;

import org.springframework.data.repository.CrudRepository;
import pl.programowaniezespolowe.projekt.model.QuizHistory;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface QuizRepository extends CrudRepository<QuizHistory, Long> {

    List<QuizHistory> findAllByUserId(Long userId);

    Optional<QuizHistory> findByUuid(UUID uuid);
}
