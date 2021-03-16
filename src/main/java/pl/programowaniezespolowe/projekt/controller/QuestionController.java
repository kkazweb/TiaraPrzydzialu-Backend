package pl.programowaniezespolowe.projekt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.programowaniezespolowe.projekt.model.Question;
import pl.programowaniezespolowe.projekt.service.QuestionServiceImpl;

import java.util.Optional;

@RestController
@RequestMapping("/api/questions")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class QuestionController {

    private QuestionServiceImpl questionService;

    @Autowired
    public QuestionController(QuestionServiceImpl questionService) {
        this.questionService = questionService;
    }

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
