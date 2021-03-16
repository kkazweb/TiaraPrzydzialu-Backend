package pl.programowaniezespolowe.projekt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.programowaniezespolowe.projekt.model.Answer;
import pl.programowaniezespolowe.projekt.repository.AnswerRepository;

@Service
public class AnswerServiceImpl {

    private AnswerRepository answerRepository;

    @Autowired
    public AnswerServiceImpl(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public Answer findAnswerById(Long id){ return answerRepository.findAnswerById(id);}
}
