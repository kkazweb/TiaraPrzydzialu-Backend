package pl.programowaniezespolowe.projekt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.programowaniezespolowe.projekt.model.Question;
import pl.programowaniezespolowe.projekt.service.QuestionServiceImpl;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    private QuestionServiceImpl questionService;

    @Autowired
    public QuestionController(QuestionServiceImpl questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/all")
    public Iterable<Question> getAll(){
        return questionService.findAll();
    }

    @GetMapping
    public Optional<Question> getById(@RequestParam Long id){
        return questionService.findById(id);
    }


}
