package pl.programowaniezespolowe.projekt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.programowaniezespolowe.projekt.model.Answer;
import pl.programowaniezespolowe.projekt.repository.AnswerRepository;

import java.util.Optional;

@Service
public class AnswerServiceImpl implements AnswerService {

    private AnswerRepository answerRepository;

    @Autowired
    public AnswerServiceImpl(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public Optional<Answer> findById(Long id){
        return answerRepository.findById(id);
    }

    public Iterable<Answer> findAll(){
        return answerRepository.findAll();
    }
}
