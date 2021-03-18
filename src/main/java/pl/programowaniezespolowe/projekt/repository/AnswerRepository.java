package pl.programowaniezespolowe.projekt.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.programowaniezespolowe.projekt.model.Answer;

import java.util.List;

@Repository
public interface AnswerRepository extends CrudRepository<Answer, Long> {
    List<Answer> findAnswersByQuestionId(Long questionId);

    Answer findAnswerById(Long id);
}
