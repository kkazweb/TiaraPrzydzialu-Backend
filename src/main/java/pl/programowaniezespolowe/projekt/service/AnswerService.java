package pl.programowaniezespolowe.projekt.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.programowaniezespolowe.projekt.model.Answer;
import pl.programowaniezespolowe.projekt.repository.AnswerRepository;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AnswerService {

    private final AnswerRepository answerRepository;

    public Answer findAnswerById(Long id){ return answerRepository.findAnswerById(id);}
}
