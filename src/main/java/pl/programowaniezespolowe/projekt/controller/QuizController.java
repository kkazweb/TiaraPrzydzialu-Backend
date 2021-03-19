package pl.programowaniezespolowe.projekt.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.programowaniezespolowe.projekt.model.Quiz;
import pl.programowaniezespolowe.projekt.service.AlgorithmService;

@RestController
@RequestMapping("/quiz")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class QuizController {

    private final AlgorithmService algorithmService;

    @ModelAttribute(name = "quiz")
    public Quiz quiz(){
        return new Quiz();
    }

    @GetMapping("/start")
    public Quiz startQuiz(){
        return this.algorithmService.startQuiz();
    }

    @PostMapping("/form")
    public Quiz showForm(@RequestBody Quiz quiz){
        return this.algorithmService.showForm(quiz);
    }

}
