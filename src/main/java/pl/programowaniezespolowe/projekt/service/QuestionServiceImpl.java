package pl.programowaniezespolowe.projekt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.programowaniezespolowe.projekt.model.Question;
import pl.programowaniezespolowe.projekt.repository.QuestionRepository;

import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService{

    private QuestionRepository questionRepository;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public Optional<Question> findById(Long id){
        return questionRepository.findById(id);
    }

    public Iterable<Question> findAll(){
        return questionRepository.findAll();
    }

    public void deleteById(Long id){
        questionRepository.deleteById(id);
    }

    public Iterable<Question> findAllByGroupCode(String code){
        return questionRepository.findQuestionsByGroupCode(code);
    }
}
