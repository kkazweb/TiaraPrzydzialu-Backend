package pl.programowaniezespolowe.projekt.repository;

import org.springframework.data.repository.CrudRepository;
import pl.programowaniezespolowe.projekt.model.QuizHistory;

import java.util.List;

public interface QuizRepository extends CrudRepository<QuizHistory, Long> {

    List<QuizHistory> findAllByUserId(Long userId);
}
