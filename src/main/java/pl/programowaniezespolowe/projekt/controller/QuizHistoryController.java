package pl.programowaniezespolowe.projekt.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.programowaniezespolowe.projekt.dto.QuizDTO;
import pl.programowaniezespolowe.projekt.model.Quiz;
import pl.programowaniezespolowe.projekt.model.QuizHistory;
import pl.programowaniezespolowe.projekt.payload.request.HistoryRequest;
import pl.programowaniezespolowe.projekt.service.QuizHistoryService;
import pl.programowaniezespolowe.projekt.service.UserDetailsServiceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/quizhistory")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class QuizHistoryController {

    private final QuizHistoryService quizHistoryService;

    private final UserDetailsServiceImpl userDetailsService;

    @PostMapping("/toQuiz")
    private Quiz fromJSONToQuiz(@RequestBody String q) throws IOException, JsonProcessingException {
        return this.quizHistoryService.fromJSONtoQuiz(q);
    }

    @PostMapping("/toString")
    private String fromQuizToJSON(@RequestBody Quiz quiz) throws IOException, JsonProcessingException{
        return this.quizHistoryService.fromQuizToJson(quiz);
    }

    @GetMapping("/all")
    private List<QuizHistory> findAll(){
        return this.quizHistoryService.findAll();
    }

    @PostMapping("/save")
    private String saveQuizInHistory(@RequestBody QuizDTO quizDTO) throws IOException, JsonProcessingException{
        Quiz quiz = fromJSONToQuiz(quizDTO.getQuiz());
        if (quiz == null) {
            return "Nie dodano quizu.";
        }

        this.quizHistoryService.saveQuizFromString(quizDTO.getUserId(), quizDTO.getQuiz());
        return "Dodano quiz";
    }

    @PostMapping("/getuserhistory")
    private List<QuizHistory> getHistoryForUser(@RequestBody HistoryRequest historyRequest){
        if(userDetailsService.findById(historyRequest.getUserId()).getUsername().equals(historyRequest.getUsername())){
            return this.quizHistoryService.findAllForUser(historyRequest.getUserId());
        }else{
            throw new UsernameNotFoundException("Nie znaleziono uzytkownika z danym ID i username.");
        }
    }

    @GetMapping("/{uuid}")
    private QuizHistory getQuizByUuid(@PathVariable UUID uuid){
        return this.quizHistoryService.findByUuid(uuid);
    }
}
