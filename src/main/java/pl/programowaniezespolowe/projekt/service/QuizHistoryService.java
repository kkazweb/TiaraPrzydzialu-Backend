package pl.programowaniezespolowe.projekt.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;
import pl.programowaniezespolowe.projekt.configuration.AuthEntryPointJwt;
import pl.programowaniezespolowe.projekt.model.Quiz;
import pl.programowaniezespolowe.projekt.model.QuizHistory;
import pl.programowaniezespolowe.projekt.model.User;
import pl.programowaniezespolowe.projekt.repository.QuizRepository;
import pl.programowaniezespolowe.projekt.repository.UserRepository;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class QuizHistoryService {

    private static final Logger logger = LoggerFactory.getLogger(Quiz.class);

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private UserRepository userRepository;

    public QuizHistory findByUuid(UUID uuid){
        return quizRepository.findByUuid(uuid).orElseThrow(() -> new ExpressionException("Quiz with UUID " + uuid + " not found."));
    }

    public List<QuizHistory> findAllForUser(Long id){
        return quizRepository.findAllByUserId(id);
    }

    public List<QuizHistory> findAll(){
        return (List<QuizHistory>) quizRepository.findAll();
    }

    public void saveQuizFromQuizObject(Long userId, Quiz quiz) throws JsonProcessingException {
        String s = fromQuizToJson(quiz);
        QuizHistory quizHistory = new QuizHistory();
        quizHistory.setUserId(userId);
        quizHistory.setQuiz(s);
        quizHistory.setUuid(UUID.randomUUID());
        Date date = new Date();
        quizHistory.setDate(date);
        Long id = quizRepository.save(quizHistory).getId();
        System.out.println("Saving with id: " + id);
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()){
            optionalUser.get().addQuizHistoryId(id);
            userRepository.save(optionalUser.get());
            System.out.println("Updating user: " + optionalUser.get());
        }
    }

    public void saveQuizFromString(Long userId, String s) {
        QuizHistory quizHistory = new QuizHistory();
        quizHistory.setUserId(userId);
        quizHistory.setQuiz(s);
        quizHistory.setUuid(UUID.randomUUID());
        Date date = new Date();
        quizHistory.setDate(date);
        Long id = quizRepository.save(quizHistory).getId();
        System.out.println("Saving with id: " + id);
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isPresent()){
            optionalUser.get().addQuizHistoryId(id);
            userRepository.save(optionalUser.get());
            System.out.println("Updating user: " + optionalUser.get());
        }

    }

    public Quiz fromJSONtoQuiz(String q) throws IOException, JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(q, Quiz.class); // jsonProcessingException
        } catch (Exception e) {
            logger.error("Error in: Quiz as string: {}", e.getMessage());
        }
        return null;
    }

    public String fromQuizToJson(Quiz quiz) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(quiz);
    }

}
