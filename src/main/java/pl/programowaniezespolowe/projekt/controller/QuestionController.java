package pl.programowaniezespolowe.projekt.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.programowaniezespolowe.projekt.model.Question;
import pl.programowaniezespolowe.projekt.service.QuestionService;

@RestController
@RequestMapping("/api/questions")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class QuestionController {

    private final QuestionService questionService;

    // to jest tylko i wyłącznie do zaaktualizowania bazy danych o similar questions
    @GetMapping("/similarquestions")
    public String hello(){
        questionService.checkForSimilarQuestions();
        return "Database updated with similar questions.";
    }

    @GetMapping("/all")
    public Iterable<Question> getAll(){
        return questionService.findAll();
    }

    @GetMapping
    public Question getById(@RequestParam Long id){
        return questionService.findById(id);
    }

}
