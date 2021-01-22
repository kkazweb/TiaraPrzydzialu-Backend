package pl.programowaniezespolowe.projekt.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.programowaniezespolowe.projekt.model.Answer;
import pl.programowaniezespolowe.projekt.model.Question;

import java.util.List;

@Repository
public interface QuestionRepository extends CrudRepository<Question, Long> {
    //List<Question> findByGroupCodeIsNull();
    List<Question> findQuestionByGroupCode(String code);

    Question findQuestionByQuestionId(Long questionId);
}
